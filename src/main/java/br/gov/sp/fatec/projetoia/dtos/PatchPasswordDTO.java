package br.gov.sp.fatec.projetoia.dtos;

public class PatchPasswordDTO {
    private Long userId;
    private String oldPassword;
    private String newPassword;
    public Long getUserId(){
        return this.userId;
    }
    public void setUserId(Long userId){
        this.userId = userId;
    }
    public String getOldPassword(){
        return this.oldPassword;
    }
    public void setOldPassword(String oldPassword){
        this.oldPassword = oldPassword;
    }
    public String getNewPassword(){
        return this.newPassword;
    }
    public void setNewPassword(String newPassword){
        this.newPassword = newPassword;
    }
}
