package com.hedgehog.client.list.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AttachmentDTO2 {
    private int no;
    private int productCode;
    private String originalName;
    private String savedName;
    private String savePath;
    private String fileType;
    private String thumbnailPath;
    private String status;
}
