//package Structural;
//
//import javax.swing.*;
//import java.util.ArrayList;
//
//public class ExampleComposite {
//
//    public static void main(String[] args){
//        Housing building = new Housing("123 Street");
//        Housing floor1 = new Housing("123 Street - 1st floor");
//        int firstFloor = building.addStructure(floor1);
//
//        Room washroom1m = new Room("1F Men's room");
//        Room washroom1f = new Room("1F Women's room");
//        Room common1 = new Room("1F Common Area");
//
//        int firstMens = floor1.addStructure(washroom1m);
//        int firstWomens = floor1.addStructure(washroom1f);
//        int firstCommon = floor1.addStructure(common1);
//
//        building.enter();
//        Housing currentFloor = (Housing) building.getStructure(firstFloor);
//        currentFloor.enter();
//        Room currentRoom = (Room) currentFloor.getStructure(firstMens);
//        currentRoom.enter();
//        currentRoom.exit();
//        currentRoom = (Room) currentFloor.getStructure(firstCommon);
//        currentRoom.enter();
//    }
//}
//
//// Interface that defines the overall type
//interface IStructure {
//    public void enter();
//    public void exit();
//    public void location();
//    public String getName();
//}
//
//// Composite class. May contain other components of the same type (Recursive composition)
//class Housing implements IStructure {
//
//    private ArrayList<IStructure> structures;
//    private String address;
//
//    Housing(String address) {
//        this.structures = new ArrayList<IStructure>();
//        this.address = address;
//    }
//
//    public int addStructure(IStructure struct){
//        this.structures.add(struct);
//        return this.structures.size() - 1;
//    }
//
//    public IStructure getStructure(int componentNumber){
//        return this.structures.get(componentNumber);
//    }
//
//    @Override
//    public void enter() {
//        System.out.println("Entering house " + address);
//    }
//
//    @Override
//    public void exit() {
//        System.out.println("Exiting house " + address);
//    }
//
//    @Override
//    public void location() {
//        System.out.println("You are currently in: " + this.getName() + ". It has ");
//        for (IStructure struct : this.structures)
//            System.out.println(struct.getName());
//    }
//
//    @Override
//    public String getName() {
//        return this.address;
//    }
//}
//
//// Leaf class. Same structure but is not composite
//class Room implements IStructure {
//
//    public String name;
//
//    public Room(String name){
//        this.name = name;
//    }
//
//    @Override
//    public void enter() {
//        System.out.println("Now entering room " + this.name);
//    }
//
//    @Override
//    public void exit() {
//        System.out.println("Now exiting room " + this.name);
//    }
//
//    @Override
//    public void location() {
//        System.out.println("You are currently in " + this.name);
//    }
//
//    @Override
//    public String getName() {
//        return this.name;
//    }
//}

// --------------------------------------------------------------------------------------------------
// Esses dois funcionam, um ta comentado pq era um exercicio, anyway... Composite OK! jacalé
package Structural;
import java.util.ArrayList;
public class ExampleComposite {

    public static void main(String args[]) {

        // Make new empty "Study" playlist
        Playlist studyPlaylist = new Playlist("Study");

        // Make "Synth Pop" playlist and add 2 songs to it.
        Playlist synthPopPlaylist = new Playlist("Synth Pop");
        Song synthPopSong1 = new Song("Girl Like You", "Toro Y Moi" );
        Song synthPopSong2 = new Song("Outside", "TOPS");
        synthPopPlaylist.add(synthPopSong1);
        synthPopPlaylist.add(synthPopSong2);

        // Make "Experimental" playlist and add 3 songs to it,
        // then set playback speed of the playlist to 0.5x
        Playlist experimentalPlaylist = new Playlist("Experimental");
        Song experimentalSong1 = new Song("About you", "XXYYXX");
        Song experimentalSong2 = new Song("Motivation", "Clams Casino");
        Song experimentalSong3 = new Song("Computer Vision", "Oneohtrix Point Never");
        experimentalPlaylist.add(experimentalSong1);
        experimentalPlaylist.add(experimentalSong2);
        experimentalPlaylist.add(experimentalSong3);
        float slowSpeed = 0.5f;
        experimentalPlaylist.setPlaybackSpeed(slowSpeed);

        // Add the "Synth Pop" playlist to the "Experimental" playlist
        experimentalPlaylist.add(synthPopPlaylist);

        // Add the "Experimental" playlist to the "Study" playlist
        studyPlaylist.add(experimentalPlaylist);

        // Create a new song and set its playback speed to 1.25x, play this song,
        // get the name of glitchSong → "Textuell", then get the artist of this song → "Oval"
        Song glitchSong = new Song("Textuell", "Oval");
        float fasterSpeed = 1.25f;
        glitchSong.setPlaybackSpeed(fasterSpeed);
        glitchSong.play();
        String name = glitchSong.getName();
        String artist = glitchSong.getArtist();
        System.out.println ("The song name is " + name );
        System.out.println ("The song artist is " + artist );

        // Add glitchSong to the "Study" playlist
        studyPlaylist.add(glitchSong);

        // Play "Study" playlist.
        studyPlaylist.play();

        // Get the playlist name of studyPlaylist → "Study"
        System.out.println ("The Playlist's name is " + studyPlaylist.getName() );
    }
}


interface IComponent {
    void setPlaybackSpeed(float speed);
    void play();
    String getName();
}


class Playlist implements IComponent {

    public String playlistName;
    public ArrayList<IComponent> playlist = new ArrayList();

    public Playlist(String playlistName) {
        this.playlistName = playlistName;
    }

    public void add(IComponent component){
        this.playlist.add(component);
    }

    public void remove(IComponent component){
        this.playlist.remove(component);
    }

    @Override
    public String getName(){
        return this.playlistName;
    }

    @Override
    public void setPlaybackSpeed(float speed) {
        System.out.println("Setting playlist speed to " + speed);
        for (IComponent song : this.playlist){
            song.setPlaybackSpeed(speed);
        }
    }

    @Override
    public void play() {
        System.out.println("Now playing playlist " + playlistName);
        for (IComponent song : this.playlist){
            song.play();
        }
    }
}


class Song implements IComponent {
    public String songName;
    public String artist;
    public float speed = 1; // Default playback speed

    public Song(String songName, String artist ) {
        this.songName = songName;
        this.artist = artist;
    }

    @Override
    public void setPlaybackSpeed(float speed) {
        System.out.println("Setting playback speed to " + speed);
        this.speed = speed;
    }

    @Override
    public void play() {
        System.out.println("Now playing " + this.songName);
    }

    @Override
    public String getName() {
        return this.songName;
    }

    public String getArtist() {
        return this.artist;
    }

}