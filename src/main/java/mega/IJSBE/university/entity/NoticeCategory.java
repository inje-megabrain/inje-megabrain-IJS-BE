package mega.IJSBE.university.entity;

import lombok.Getter;

public enum NoticeCategory {
    CS("컴공") ,
    HS("학사") ,
    GN("일반");
    @Getter
    private final String value;
    NoticeCategory(String value){
        this.value = value;
    }
}
