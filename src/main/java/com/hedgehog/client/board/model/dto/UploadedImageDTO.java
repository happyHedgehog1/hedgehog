package com.hedgehog.client.board.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UploadedImageDTO {
    private String convertPath;
    private String savePath;
    private String sourceName;
    private String convertName;
}
