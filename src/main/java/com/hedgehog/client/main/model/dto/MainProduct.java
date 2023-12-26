package com.hedgehog.client.main.model.dto;

import com.hedgehog.client.list.dto.ProductListDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MainProduct {

    private List<ProductListDTO> MainProduct;
}
