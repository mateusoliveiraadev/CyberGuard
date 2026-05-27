// src/main/resources/static/api.js

// Como o front está na mesma porta do back (8080), 
// podemos usar o caminho relativo direto.
const BASE_URL = "/usuarios";

export const api = {
    // Chama o método register do UsuarioController
    cadastrar: async (dados) => {
        const response = await fetch(`${BASE_URL}/register`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(dados)
        });
        if (!response.ok) {
            const errorText = await response.text();
            throw new Error(errorText || "Erro ao cadastrar");
        }
        return response.json();
    },

    // Chama o método login do UsuarioController
    login: async (dados) => {
        const response = await fetch(`${BASE_URL}/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(dados)
        });
        
        if (!response.ok) {
            // Se o Spring retornar 401 (que configuramos no Controller), cai aqui
            throw new Error("E-mail ou senha incorretos");
        }
        return response.json();
    }
};