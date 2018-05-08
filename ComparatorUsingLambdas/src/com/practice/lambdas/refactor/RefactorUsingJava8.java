package com.practice.lambdas.refactor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RefactorUsingJava8 {
    public static void main(String args[]) {
        Track t1 = new Track("mein hoon na", 120);
        Track t2 = new Track("jo jeeta wohi sikandar", 60);
        Track t3 = new Track("ye jo desh hai", 55);
        List<Track> list1 = new ArrayList<>();
        list1.add(t1);
        list1.add(t2);
        list1.add(t3);
        Albums a1 = new Albums("album1", list1);
        Track t4 = new Track("test", 30);
        Track t5 = new Track("test again", 50);
        Track t6 = new Track("test again & again", 155);
        List<Track> list2 = new ArrayList<>();
        list2.add(t4);
        list2.add(t5);
        list2.add(t6);
        Albums a2 = new Albums("album2", list2);
        List<Albums> listAlbums = new ArrayList<>();
        listAlbums.add(a1);
        listAlbums.add(a2);
        Set<String> albumNames = getTracksLengthLessThanSixtyLegacy(listAlbums);
        Set<String> newWayAlbumNames = getTracksLengthLessThanSixtyLambda(listAlbums);
        Set<String> newEffectiveWayAlbumNames = getTracksLengthLessThanSixtyLambdaEffective(listAlbums);
    }

    /***
     * The imperative or the legacy way of doing things.
     * @param listAlbums
     * @return Set<String> containing the track names whose length is less than 60
     */
    private static Set<String> getTracksLengthLessThanSixtyLegacy(List<Albums> listAlbums) {
        Set<String> trackNames = new HashSet<>();
        for (Albums album : listAlbums) {
            List<Track> track = album.getTracks();
            for (Track tr : track) {
                if (tr.getLength() < 60)
                    trackNames.add(tr.getName());
            }
        }
        return trackNames;
    }

    /***
     * Functional but a little less effective way of doing things.
     * @param listAlbums
     * @return Set<String> containing the track names whose length is less than 60
     */
    private static Set<String> getTracksLengthLessThanSixtyLambda(List<Albums> listAlbums) {
        Set<String> trackNames = new HashSet<>();
        listAlbums.stream().forEach(albums -> albums.getTracks()
                .stream().
                        filter(track->track.getLength()<60).
                        forEach(track -> trackNames.add(track.getName())));
        return trackNames;
    }

    /***
     * Functional & Effective way to do things.
     * @param listAlbums
     * @return Set<String> containing the track names whose length is less than 60
     */
    private static Set<String> getTracksLengthLessThanSixtyLambdaEffective(List<Albums> listAlbums) {
        return listAlbums.stream().map(album -> album.getTracks()).
                flatMap(tracks -> tracks.stream())
                .filter(track -> track.getLength()<60).map(track->track.getName()).collect(Collectors.toSet());

    }
}
