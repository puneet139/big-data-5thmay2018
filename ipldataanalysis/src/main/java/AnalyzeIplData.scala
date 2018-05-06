import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object AnalyzeIplData extends  App {
  val conf = new SparkConf().setAppName("Ipl-Data-Analysis").setMaster("local")
  val sc = new SparkContext(conf)
  val iplDataRDD = sc.textFile("file:///D:/kaggle_ipl_Data/deliveries.csv")
  //iplDataRDD.take(3).foreach(println)
  val firstline = iplDataRDD.first()
  val iplDataMappedRDD = iplDataRDD.filter(line=>line!=firstline)
  iplDataMappedRDD.take(3).foreach(println)
  val iplDataFirstInningsSplit = iplDataMappedRDD.map(line=>{
    val temp = line.split(",",-1)
    for (i<- 0 to temp.length-1)
      {
        if(temp(i)=="")
          temp(i)="0"
      }

    ((temp(0),temp(1),temp(2)),(temp(17).toInt,temp(19)))
  }).filter(line=>(line._1._2=="1" || line._1._2=="3"))

  iplDataFirstInningsSplit.take(3).foreach(println)

  val iplDataScore = iplDataFirstInningsSplit.map(line=>{
      if(line._2._2!="0")
      {
        (line._1,(line._2._1,"1".toInt))
      }else
      {
        (line._1,(line._2._1,"0".toInt))
      }}).reduceByKey((sum,count)=>((sum._1+count._1),sum._2+count._2))

  val iplDataFirstJoinset = iplDataScore.map(line=>((line._1._1),(line._1._2,line._1._3,line._2._1,line._2._2))).sortByKey()
  iplDataFirstInningsSplit.take(3).foreach(println)
  val iplDataSecondInningsSplit = iplDataMappedRDD.map(line=>{
    val temp = line.split(",",-1)
    for (i<- 0 to temp.length-1)
    {
      if(temp(i)=="")
        temp(i)="0"
    }

    ((temp(0),temp(1),temp(2)),(temp(17).toInt,temp(19)))
  }).filter(line=>(line._1._2=="2" || line._1._2=="4" ))


  val iplDataScoreSecond = iplDataSecondInningsSplit.map(line=>{
    if(line._2._2!="0")
    {
      (line._1,(line._2._1,"1".toInt))
    }else
    {
      (line._1,(line._2._1,"0".toInt))
    }}).reduceByKey((sum,count)=>((sum._1+count._1),sum._2+count._2))

  val iplDataSecondJoinset = iplDataScoreSecond.map(line=>((line._1._1),(line._1._2,line._1._3,line._2._1,line._2._2))).sortByKey()

  val iplDataJoinedSet = iplDataFirstJoinset.join(iplDataSecondJoinset)

  val result = iplDataJoinedSet.map(line=>{
    if(line._2._1._3>line._2._2._3)
      (line._1.toString, line._2._1._2.toString, line._2._2._2.toString, (line._2._1._3 - line._2._2._3) + " runs", line._2._1._3.toString + "/" + line._2._1._4.toString, line._2._2._3.toString + "/" + line._2._2._4.toString)
    else if(line._2._1._3<line._2._2._3)
      {
        (line._1.toString, line._2._2._2.toString, line._2._1._2.toString, (10 - line._2._2._4) + " wickets", line._2._2._3.toString + "/" + line._2._2._4.toString, line._2._1._3.toString + "/" + line._2._1._4.toString)
      }
  }).map(line=>Array(line).mkString(" "))

  val headerLine = sc.parallelize(Array("Match_id,Winner Team,Losing Team,Win Margin,Winner Score,Losing Score"))
  var res = headerLine.union(result)
  res.take(25).foreach(println)
  res.saveAsTextFile("file:///D:/kaggle_ipl_Data/match_Results/results.txt")
}