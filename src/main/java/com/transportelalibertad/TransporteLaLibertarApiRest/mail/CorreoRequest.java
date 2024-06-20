package com.transportelalibertad.TransporteLaLibertarApiRest.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CorreoRequest {

    private String destinatario;
    private String asunto;
    private String contenido;

}