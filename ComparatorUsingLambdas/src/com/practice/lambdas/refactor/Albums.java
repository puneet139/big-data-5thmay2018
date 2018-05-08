package com.practice.lambdas.refactor;

import java.util.List;

public class Albums {

    private String albumName;
    private List<Track> tracks;

    public Albums(String albumName, List<Track> track)
    {
        this.albumName=albumName;
        this.tracks=track;
    }

    public void setAlbumName(String name)
    {
        this.albumName = name;
    }

    public String getAlbumName()
    {
        return albumName;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }
}
