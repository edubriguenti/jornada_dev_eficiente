package com.jornadadev.mercadolivre.cadastro.produto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class NovasImagensRequest {

    @Size(min = 1)
    @NotNull
    private List<MultipartFile> imagens;

}
