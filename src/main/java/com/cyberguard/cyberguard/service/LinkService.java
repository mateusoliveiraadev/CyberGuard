package com.cyberguard.cyberguard.service;

import java.net.URI;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cyberguard.cyberguard.entity.LinkResponse;

@Service
public class LinkService {

    private final List<String> palavrasSuspeitas = List.of(
            "login",
            "free",
            "bonus",
            "pix",
            "senha",
            "premio",
            "gift",
            "win"
    );

    public LinkResponse verificarLink(String url) {

        if (!urlValida(url)) {
            return new LinkResponse(
                    false,
                    "Link inválido. Digite uma URL válida."
            );
        }

        String urlLower = url.toLowerCase();

        for (String palavra : palavrasSuspeitas) {

            if (urlLower.contains(palavra)) {

                return new LinkResponse(
                        false,
                        "Link suspeito! Possível tentativa de golpe."
                );
            }
        }

        if (url.contains("@") || url.contains("--")) {

            return new LinkResponse(
                    false,
                    "Link suspeito! Caracteres incomuns detectados."
            );
        }

        return new LinkResponse(
                true,
                "Link aparentemente seguro."
        );
    }

    private boolean urlValida(String url) {

        try {

            URI uri = new URI(url);

            return uri.getScheme() != null
                    && (
                    uri.getScheme().equals("http")
                            || uri.getScheme().equals("https")
            );

        } catch (Exception e) {

            return false;
        }
    }
}