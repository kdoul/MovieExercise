package gr.unipi.msc.android.movieexercise.datamanager.model;

public class UserProfile {

    private Avatar avatar;
    private final int ID;
    private final String name;
    private final String username;

    public UserProfile(Avatar avatar, int ID, String name, String username) {
        this.avatar = avatar;
        this.ID = ID;
        this.name = name;
        this.username = username;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public class Avatar {
        private Gravatar gravatar;

        public Gravatar getGravatar() {
            return gravatar;
        }

        public Avatar(Gravatar gravatar) {
            this.gravatar = gravatar;

        }

        public class Gravatar {
            private String hash;
            public Gravatar(String hash){
                this.hash = hash;
            }
            public String getHash(){
                return hash;
            }
        }
    }
}
