package jp.co.jeus.blog.web.dto;

import lombok.Getter;

@Getter
public class ImageResponseDto {

    private String imageName;

    public ImageResponseDto(String imageName) {
        this.imageName = imageName;
    }
}
