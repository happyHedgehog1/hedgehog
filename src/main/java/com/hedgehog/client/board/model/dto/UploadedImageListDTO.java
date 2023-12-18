package com.hedgehog.client.board.model.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UploadedImageListDTO {
    private List<UploadedImageDTO> UploadedImageList;
}
