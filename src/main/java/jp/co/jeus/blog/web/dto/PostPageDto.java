package jp.co.jeus.blog.web.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class PostPageDto {
    private int totalPostNum;
    private List<PostResponseDto> posts;

    @Builder
    public PostPageDto(int totalPostNum, List<PostResponseDto> posts) {
        this.totalPostNum = totalPostNum;
        this.posts = posts;
    }
}
