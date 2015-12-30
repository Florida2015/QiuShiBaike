package cn.edu.nuc.qiushibaike.entitys;

import java.util.List;

/**
 * Created by Flming2015 on 2015/12/30.
 */
public class CunTuComment {

    /**
     * count : 2
     * items : [{"liked":false,"floor":1,"created_at":1451465183,"content":"我去，中毒了","like_count":0,"user":{"last_visited_at":1387539433,"created_at":1387539433,"last_device":"ios_2.6.3","email":"602424954@qq.com","state":"bonding","role":"n","login":"起啥名字嘞","id":12998089,"icon":"20131228143007.jpg"},"id":346459852}]
     * total : 1
     * page : 1
     * err : 0
     */

    private int count;
    private int total;
    private int page;
    private int err;
    /**
     * liked : false
     * floor : 1
     * created_at : 1451465183
     * content : 我去，中毒了
     * like_count : 0
     * user : {"last_visited_at":1387539433,"created_at":1387539433,"last_device":"ios_2.6.3","email":"602424954@qq.com","state":"bonding","role":"n","login":"起啥名字嘞","id":12998089,"icon":"20131228143007.jpg"}
     * id : 346459852
     */

    private List<ItemsEntity> items;

    public void setCount(int count) {
        this.count = count;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public void setItems(List<ItemsEntity> items) {
        this.items = items;
    }

    public int getCount() {
        return count;
    }

    public int getTotal() {
        return total;
    }

    public int getPage() {
        return page;
    }

    public int getErr() {
        return err;
    }

    public List<ItemsEntity> getItems() {
        return items;
    }

    public static class ItemsEntity {
        private boolean liked;
        private int floor;
        private int created_at;
        private String content;
        private int like_count;
        /**
         * last_visited_at : 1387539433
         * created_at : 1387539433
         * last_device : ios_2.6.3
         * email : 602424954@qq.com
         * state : bonding
         * role : n
         * login : 起啥名字嘞
         * id : 12998089
         * icon : 20131228143007.jpg
         */

        private UserEntity user;
        private int id;

        public void setLiked(boolean liked) {
            this.liked = liked;
        }

        public void setFloor(int floor) {
            this.floor = floor;
        }

        public void setCreated_at(int created_at) {
            this.created_at = created_at;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setLike_count(int like_count) {
            this.like_count = like_count;
        }

        public void setUser(UserEntity user) {
            this.user = user;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isLiked() {
            return liked;
        }

        public int getFloor() {
            return floor;
        }

        public int getCreated_at() {
            return created_at;
        }

        public String getContent() {
            return content;
        }

        public int getLike_count() {
            return like_count;
        }

        public UserEntity getUser() {
            return user;
        }

        public int getId() {
            return id;
        }

        public static class UserEntity {
            private int last_visited_at;
            private int created_at;
            private String last_device;
            private String email;
            private String state;
            private String role;
            private String login;
            private int id;
            private String icon;

            public void setLast_visited_at(int last_visited_at) {
                this.last_visited_at = last_visited_at;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }

            public void setLast_device(String last_device) {
                this.last_device = last_device;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public void setState(String state) {
                this.state = state;
            }

            public void setRole(String role) {
                this.role = role;
            }

            public void setLogin(String login) {
                this.login = login;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public int getLast_visited_at() {
                return last_visited_at;
            }

            public int getCreated_at() {
                return created_at;
            }

            public String getLast_device() {
                return last_device;
            }

            public String getEmail() {
                return email;
            }

            public String getState() {
                return state;
            }

            public String getRole() {
                return role;
            }

            public String getLogin() {
                return login;
            }

            public int getId() {
                return id;
            }

            public String getIcon() {
                return icon;
            }
        }
    }
}
