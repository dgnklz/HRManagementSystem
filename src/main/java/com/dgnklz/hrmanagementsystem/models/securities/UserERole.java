package com.dgnklz.hrmanagementsystem.models.securities;

public enum UserERole{
    //ROLE_USER,
    ROLE_MODERATOR,
    ROLE_ADMIN

    /*ADMIN("admin"),
    MODERATOR("mod");

    private String userRoleName;
    UserRole(String userRoleName) {
        this.userRoleName = userRoleName;
    }
    public String getUserRoleName() {
        return userRoleName;
    }
    static public boolean isMember(String roleName) {
        UserRole[] roles = UserRole.values();
        for (UserRole role : roles)
            if(role.userRoleName.equals(roleName))
                return true;
        return false;
    }*/

}