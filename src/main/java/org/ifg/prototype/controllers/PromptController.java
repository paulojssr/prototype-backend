package org.ifg.prototype.controllers;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.ifg.prototype.dto.FakeDadosReqDTO;
import org.ifg.prototype.dto.FakeRequisitosDTO;
import org.ifg.prototype.dto.PromptDTO;
import org.ifg.prototype.entities.Prompt;
import org.ifg.prototype.entities.Usuario;
import org.ifg.prototype.services.PromptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

/**
 * Um controlador para a entidade {@link Prompt}.
 * Crated by Daniel 04/06/2023.
 * $ será o token de inserção da variável no texto
 * pode haver mais de um $ no texto
 * TODO - Reavaliar todo a regra de prompt, se há realmente necessidade de ter um prompt modelo persistido em banco,
 *      - ou se o prompt modelo pode ser gerado dinamicamente a partir dos dados de entrada com código pre-definido,
 *      - sem a necessidade de dezenas ou até centenas de prompts modelo persistidos em banco.
 */
@SuppressWarnings("SpellCheckingInspection")
@RestController
@RequestMapping(value = "/prompts", produces = "application/json")
public class PromptController {

    @Autowired
    private PromptService promptService;

    //marcação para inserção de texto no prompt modelo
    private final String marcador = "[INSIRA_O_TEXTO_AQUI]";
    //marcação para início de bloco de texto a ser inserido no prompt modelo
    private final String palavraToken1 = "<body>";
    //marcação para fim de bloco de texto a ser inserido no prompt modelo
    private final String palavraToken2 = "</body>";

    private String htmlModelo = "<!DOCTYPE html>\n" +
            "<html lang=\"pt-BR\">\n" +
            "\n" +
            "<head>\n" +
            "    <title>Bloco</title>\n" +
            "    <meta charset=\"utf-8\">\n" +
            "    <style>\n" +
            "        @import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap');\n" +
            "\n" +
            "        @-moz-document url-prefix() {\n" +
            "\n" +
            "            /* Estilos específicos para o Firefox */\n" +
            "            input[type=\"radio\"] {\n" +
            "                transform: scale(3.5);\n" +
            "                margin-right: 5px;\n" +
            "                /* Adicione margem à direita para separar os radio buttons */\n" +
            "            }\n" +
            "        }\n" +
            "\n" +
            "        body {\n" +
            "            background-color: #d9d9e3;\n" +
            "            display: flex;\n" +
            "            justify-content: center;\n" +
            "            align-items: center;\n" +
            "            margin: 0;\n" +
            "        }\n" +
            "\n" +
            "        .container {\n" +
            "            width: 1000px;\n" +
            "            border: 1px solid #a7b0d3;\n" +
            "            border-radius: 14px;\n" +
            "            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);\n" +
            "            background-color: #ffffff;\n" +
            "            font-family: 'Roboto', sans-serif;\n" +
            "            display: flex;\n" +
            "            flex-direction: column;\n" +
            "            justify-content: space-between;\n" +
            "        }\n" +
            "\n" +
            "        .container input {\n" +
            "            border-radius: 50px;\n" +
            "            border: 1px solid #73b0ff;\n" +
            "            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);\n" +
            "            box-sizing: border-box;\n" +
            "            font-size: 1.25rem;\n" +
            "            padding: 0.25em;\n" +
            "            width: 100%;\n" +
            "        }\n" +
            "\n" +
            "        .container select {\n" +
            "            border-radius: 50px;\n" +
            "            border: 1px solid #73b0ff;\n" +
            "            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);\n" +
            "            box-sizing: border-box;\n" +
            "            font-size: 1.25rem;\n" +
            "            padding: 0.25em;\n" +
            "            width: inherit;\n" +
            "        }\n" +
            "\n" +
            "        .header {\n" +
            "            padding: 10px;\n" +
            "            background-color: #a7b0d3;\n" +
            "            color: white;\n" +
            "            font-weight: bold;\n" +
            "            border-top-left-radius: 14px;\n" +
            "            border-top-right-radius: 14px;\n" +
            "        }\n" +
            "\n" +
            "        .block {\n" +
            "            display: flex;\n" +
            "            flex-direction: column;\n" +
            "            padding: 10px;\n" +
            "        }\n" +
            "\n" +
            "        .orderItems {\n" +
            "            display: flex;\n" +
            "\n" +
            "        }\n" +
            "\n" +
            "        .block-one-columns {\n" +
            "            align-items: center;\n" +
            "            display: grid;\n" +
            "            grid-template-columns: repeat(1, 1fr);\n" +
            "            gap: 10px;\n" +
            "            padding: 10px;\n" +
            "            box-sizing: border-box;\n" +
            "        }\n" +
            "\n" +
            "        .block-two-columns {\n" +
            "            align-items: center;\n" +
            "            display: grid;\n" +
            "            grid-template-columns:  repeat(2, 1fr);\n" +
            "            gap: 10px;\n" +
            "            padding: 10px;\n" +
            "            box-sizing: border-box;\n" +
            "        }\n" +
            "\n" +
            "        .block-three-columns {\n" +
            "            align-items: center;\n" +
            "            display: grid;\n" +
            "            grid-template-columns: repeat(3, 1fr);\n" +
            "            gap: 10px;\n" +
            "            padding: 10px;\n" +
            "            box-sizing: border-box;\n" +
            "        }\n" +
            "\n" +
            "        .block-four-columns {\n" +
            "            align-items: center;\n" +
            "            display: grid;\n" +
            "            grid-template-columns: repeat(4, 1fr);\n" +
            "            gap: 10px;\n" +
            "            padding: 10px;\n" +
            "            box-sizing: border-box;\n" +
            "        }\n" +
            "\n" +
            "        .block-five-columns {\n" +
            "            align-items: center;\n" +
            "            display: grid;\n" +
            "            grid-template-columns: repeat(5, 1fr);\n" +
            "            gap: 10px;\n" +
            "            padding: 10px;\n" +
            "            box-sizing: border-box;\n" +
            "        }\n" +
            "\n" +
            "        .block-six-columns {\n" +
            "            align-items: flex-start;\n" +
            "            display: flex;\n" +
            "            flex-direction: column;\n" +
            "            gap: 10px;\n" +
            "            padding: 10px;\n" +
            "            width: 50%;\n" +
            "            box-sizing: border-box;\n" +
            "        }\n" +
            "\n" +
            "\t.block-six-columns-checkbox-horizontal {\n" +
            "            align-items: center;\n" +
            "            display: grid;\n" +
            "            font-weight: bold;\n" +
            "            grid-template-columns: repeat(6, 1fr);\n" +
            "            gap: 10px;\n" +
            "            padding: 10px;\n" +
            "            box-sizing: border-box;\n" +
            "            justify-items: start;\n" +
            "        }\n" +
            "\n" +
            "        .block-six-columns-checkbox-vertical {\n" +
            "            align-items: flex-start;\n" +
            "            display: flex;\n" +
            "            font-weight: bold;\n" +
            "            flex-direction: column;\n" +
            "            gap: 10px;\n" +
            "            padding: 10px;\n" +
            "            box-sizing: border-box;\n" +
            "            justify-items: start;\n" +
            "        }\n" +
            "\n" +
            "        .sticker {\n" +
            "            border: 1px solid #a7b0d3;\n" +
            "            background-color: #a7b0d3;\n" +
            "            height: 1px;\n" +
            "            margin: 10px 0;\n" +
            "        }\n" +
            "\n" +
            "        .button-container-align-right {\n" +
            "            display: flex;\n" +
            "            justify-content: flex-end;\n" +
            "            padding: 10px;\n" +
            "        }\n" +
            "\n" +
            "        .button-container-align-left {\n" +
            "            display: flex;\n" +
            "            justify-content: flex-start;\n" +
            "            padding: 10px;\n" +
            "        }\n" +
            "\n" +
            "        .button-container-align-center {\n" +
            "            display: flex;\n" +
            "            justify-content: center;\n" +
            "            padding: 10px;\n" +
            "        }\n" +
            "\n" +
            "        .button-container button {\n" +
            "            margin-left: 10px;\n" +
            "            border-radius: 50px;\n" +
            "            padding: 10px 20px;\n" +
            "            font-weight: bold;\n" +
            "            border: none;\n" +
            "            cursor: pointer;\n" +
            "        }\n" +
            "\n" +
            "        .button-container button.save {\n" +
            "            background-color: #36b357;\n" +
            "            color: #fff;\n" +
            "        }\n" +
            "\n" +
            "        .button-container button.clear {\n" +
            "            background-color: #fa4646;\n" +
            "            color: #fff;\n" +
            "        }\n" +
            "\n" +
            "        .footer {\n" +
            "            padding: 10px;\n" +
            "            background-color: #a7b0d3;\n" +
            "            color: white;\n" +
            "            font-weight: bold;\n" +
            "            border-bottom-left-radius: 14px;\n" +
            "            border-bottom-right-radius: 14px;\n" +
            "        }\n" +
            "\n" +
            "        .strong {\n" +
            "            font-weight: bold;\n" +
            "        }\n" +
            "\n" +
            "        table {\n" +
            "            width: inherit;\n" +
            "            margin: 10px;\n" +
            "            border-collapse: collapse;\n" +
            "            background-color: #fff;\n" +
            "            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);\n" +
            "            padding: 10px;\n" +
            "        }\n" +
            "\n" +
            "        th,\n" +
            "        td {\n" +
            "            padding: 10px;\n" +
            "            text-align: left;\n" +
            "        }\n" +
            "\n" +
            "        th {\n" +
            "            background-color: #7385de;\n" +
            "            color: #ffffff;\n" +
            "            font-weight: bold;\n" +
            "        }\n" +
            "\n" +
            "        tr:nth-child(even) {\n" +
            "            background-color: #a7b0d3;\n" +
            "            color: #ffffff;\n" +
            "        }\n" +
            "\n" +
            "        .check {\n" +
            "            align-items: flex-start;\n" +
            "            justify-content: center;\n" +
            "            display: flex;\n" +
            "            margin-top: 10px;\n" +
            "            text-align: left;\n" +
            "            border: none;\n" +
            "            font-weight: inherit;\n" +
            "            justify-items: start;\n" +
            "            height: auto;\n" +
            "        }\n" +
            "\n" +
            "        .block-six-columns label {\n" +
            "            font-weight: bold !important;\n" +
            "            padding-right: 10px;\n" +
            "            border: none;\n" +
            "        }\n" +
            "\n" +
            "        .check input[type=\"radio\"] {\n" +
            "            box-shadow: none;\n" +
            "            margin-right: 10px;\n" +
            "            padding-right: 10px;\n" +
            "            border: none;\n" +
            "            justify-items: start;\n" +
            "        }\n" +
            "\n" +
            "        .checkbox-label {\n" +
            "            display: inline-flex;\n" +
            "            margin-bottom: 5px;\n" +
            "            align-items: center;\n" +
            "            padding-right: 5px;\n" +
            "        }\n" +
            "\n" +
            "        .checkbox-label input[type=\"checkbox\"] {\n" +
            "            margin-right: 5px;\n" +
            "            padding: 0;\n" +
            "            box-shadow: none;\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "[INSIRA_O_TEXTO_AQUI]" +
            "</body>\n" +
            "</html>";

    String htmlChatGPT = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "  <title>Exemplo de Página HTML</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "  <div class=\"container\">\n" +
            "    <div id=\"titulo\" class=\"header\">\n" +
            "      <label>Cadastro de Usuário</label>\n" +
            "    </div>\n" +
            "    <div class=\"block block-one-columns\">\n" +
            "      <div>\n" +
            "        <label>Nome</label>\n" +
            "        <input type=\"text\" />\n" +
            "      </div>\n" +
            "    </div>\n" +
            "    <div class=\"block block-one-columns\">\n" +
            "      <div>\n" +
            "        <label>E-mail</label>\n" +
            "        <input type=\"email\" />\n" +
            "      </div>\n" +
            "    </div>\n" +
            "    <div class=\"block block-two-columns\">\n" +
            "      <div>\n" +
            "        <label>Senha</label>\n" +
            "        <input type=\"password\" />\n" +
            "      </div>\n" +
            "      <div>\n" +
            "        <label>Confirmar Senha</label>\n" +
            "        <input type=\"password\" />\n" +
            "      </div>\n" +
            "    </div>\n" +
            "    <div class=\"block block-two-columns\">\n" +
            "      <div>\n" +
            "        <label>CPF</label>\n" +
            "        <input type=\"text\" placeholder=\"000.000.000-00\" />\n" +
            "      </div>\n" +
            "      <div>\n" +
            "        <label>RG</label>\n" +
            "        <input type=\"text\" />\n" +
            "      </div>\n" +
            "    </div>\n" +
            "    <div class=\"block block-six-columns\">\n" +
            "      <div class=\"check\">\n" +
            "        <label>Sexo</label>\n" +
            "        <input type=\"radio\" name=\"sexo\" value=\"Feminino\" /> Feminino\n" +
            "        <input type=\"radio\" name=\"sexo\" value=\"Masculino\" /> Masculino\n" +
            "      </div>\n" +
            "    </div>\n" +
            "    <div class=\"block block-four-columns\">\n" +
            "      <div>\n" +
            "        <label>Telefone</label>\n" +
            "        <input type=\"text\" placeholder=\"(00) 00000-0000\" />\n" +
            "      </div>\n" +
            "    </div>\n" +
            "    <div class=\"block-one-columns\">\n" +
            "      <label>Lista de Países</label>\n" +
            "      <table>\n" +
            "        <tr>\n" +
            "          <th>País</th>\n" +
            "          <th>Sigla</th>\n" +
            "          <th>Código Telefone</th>\n" +
            "          <th>Código ISO</th>\n" +
            "          <th>Continente</th>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "          <td>País 1</td>\n" +
            "          <td>Sigla 1</td>\n" +
            "          <td>Código 1</td>\n" +
            "          <td>ISO 1</td>\n" +
            "          <td>Continente 1</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "          <td>País 2</td>\n" +
            "          <td>Sigla 2</td>\n" +
            "          <td>Código 2</td>\n" +
            "          <td>ISO 2</td>\n" +
            "          <td>Continente 2</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "          <td>País 3</td>\n" +
            "          <td>Sigla 3</td>\n" +
            "          <td>Código 3</td>\n" +
            "          <td>ISO 3</td>\n" +
            "          <td>Continente 3</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "          <td>País 4</td>\n" +
            "          <td>Sigla 4</td>\n" +
            "          <td>Código 4</td>\n" +
            "          <td>ISO 4</td>\n" +
            "          <td>Continente 4</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "          <td>País 5</td>\n" +
            "          <td>Sigla 5</td>\n" +
            "          <td>Código 5</td>\n" +
            "          <td>ISO 5</td>\n" +
            "          <td>Continente 5</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "          <td>País 6</td>\n" +
            "          <td>Sigla 6</td>\n" +
            "          <td>Código 6</td>\n" +
            "          <td>ISO 6</td>\n" +
            "          <td>Continente 6</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "          <td>País 7</td>\n" +
            "          <td>Sigla 7</td>\n" +
            "          <td>Código 7</td>\n" +
            "          <td>ISO 7</td>\n" +
            "          <td>Continente 7</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "          <td>País 8</td>\n" +
            "          <td>Sigla 8</td>\n" +
            "          <td>Código 8</td>\n" +
            "          <td>ISO 8</td>\n" +
            "          <td>Continente 8</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "          <td>País 9</td>\n" +
            "          <td>Sigla 9</td>\n" +
            "          <td>Código 9</td>\n" +
            "          <td>ISO 9</td>\n" +
            "          <td>Continente 9</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "          <td>País 10</td>\n" +
            "          <td>Sigla 10</td>\n" +
            "          <td>Código 10</td>\n" +
            "          <td>ISO 10</td>\n" +
            "          <td>Continente 10</td>\n" +
            "        </tr>\n" +
            "      </table>\n" +
            "    </div>\n" +
            "    <div class=\"button-container button-container-align-center\">\n" +
            "      <button class=\"button\" type=\"submit\">Salvar</button>\n" +
            "      <button class=\"button\" type=\"button\">Limpar</button>\n" +
            "    </div>\n" +
            "    <div class=\"footer\">\n" +
            "      <label>Protótipo de página HTML gerado automaticamente com auxílio do ChatGPT</label>\n" +
            "    </div>\n" +
            "  </div>\n" +
            "</body>\n" +
            "</html>\n";


    // Regex para validação de campos de entrada
    private final String regexValidaNomePrompt = "^[a-zA-Z0-9À-ÿ ]{10,60}$";
    private final String regexValidaPromptModelo = "^[a-zA-Z0-9À-ÿ .,:-]{10,5000}$";
    private String[] colunas = {"primeira coluna", "segunda coluna", "terceira coluna",
            "quarta coluna", "quinta coluna", "sexta coluna"};

    /**
     * Método que salva um prompt
     * @param promptDTO
     * @return ResponseEntity<Object>
     */
    @PostMapping(value = "/salvarprompt")
    public ResponseEntity<Object> salvarPrompt(@RequestBody PromptDTO promptDTO) {

        // Validações de campos obrigatórios e regex de validação de campos de entrada nome e prompt modelo
        var matchNomePrompt = promptDTO.getNome().matches(regexValidaNomePrompt);
        if (!matchNomePrompt) {
            return ResponseEntity.badRequest().body("O nome deve ter entre 10 e 60 caracteres" +
                    " e não pode conter caracteres especiais");
        }

        var matchPromptModelo = promptDTO.getPromptModelo().matches(regexValidaPromptModelo);
        if (!matchPromptModelo) {
            return ResponseEntity.badRequest().body("O prompt modelo deve ter entre 10 e 5000" +
                    " caracteres e não pode conter caracteres especiais");
        }

        try {
            Prompt prompt = new Prompt();
            prompt.setNome(promptDTO.getNome());
            prompt.setUsuario(new Usuario());
            prompt.getUsuario().setCodigo(promptDTO.getCodigoUsuario());
            prompt.setDataCriacao(LocalDate.ofInstant(Instant.now(), ZoneOffset.UTC));
            prompt.setPromptModelo(promptDTO.getPromptModelo());
            prompt.setCodigo(promptService.save(prompt).getCodigo());
            return ResponseEntity.ok().body(prompt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("Erro ao salvar prompt");
        }
    }

    /**
     * Método que atualiza um prompt
     * @param codigo
     * @param promptDTO
     * @return ResponseEntity<Object>
     */
    @PutMapping("/atualizarprompt/{codigo}")
    public ResponseEntity<Object> atualizarPrompt(@PathVariable("codigo") Long codigo,
                                                  @RequestBody PromptDTO promptDTO) {

        var matchNomePrompt = promptDTO.getNome().matches(regexValidaNomePrompt);
        if (!matchNomePrompt) {
            return ResponseEntity.badRequest().body("O nome deve ter entre 10 e 60 caracteres" +
                    " e não pode conter caracteres especiais");
        }

        var matchPromptModelo = promptDTO.getPromptModelo().matches(regexValidaPromptModelo);
        if (!matchPromptModelo) {
            return ResponseEntity.badRequest().body("O prompt modelo deve ter entre 10 e 5000" +
                    " caracteres e não pode conter caracteres especiais");
        }

        promptDTO.setCodigo(codigo);
        Optional<PromptDTO> promptOptional = promptService.findById(codigo);
        if (promptOptional.isPresent()) {
            Prompt prompt = new Prompt();
            prompt.setCodigo(codigo);
            prompt.setNome(promptDTO.getNome());
            prompt.setDataCriacao(promptOptional.get().getDataCriacao());
            prompt.setPromptModelo(promptDTO.getPromptModelo());
            prompt.setUsuario(promptOptional.get().getUsuario());
            promptService.update(prompt);
            return ResponseEntity.status(HttpStatus.OK).body("Prompt Atualizado!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prompt Não Encontrado!");
        }
    }

    /**
     * Método que pesquisa um prompt
     * @param codigo
     * @return ResponseEntity<Object>
     */
    @GetMapping("/pesquisarprompt/{codigo}")
    public ResponseEntity<Object> pesquisarPrompt(@PathVariable("codigo") Long codigo) {
        Optional<PromptDTO> promptOptional = promptService.findById(codigo);
        if (promptOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(promptOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prompt Não Encontrado!");
        }
    }

     /**
     * Método que deleta um prompt
     * @param codigo
     * @return ResponseEntity<Object>
     */
    @DeleteMapping("/deletarprompt/{codigo}")
    public ResponseEntity<Object> deletarPrompt(@PathVariable("codigo") Long codigo) {
        Optional<PromptDTO> promptOptional = promptService.findById(codigo);
        if (promptOptional.isPresent()) {
            promptService.delete(codigo);
            return ResponseEntity.status(HttpStatus.OK).body("Prompt Deletado!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prompt Não Encontrado!");
        }
    }

    /**
     * Método que pesquisa todos os prompts
     * @return ResponseEntity<Object>
     */
    @GetMapping
    public ResponseEntity<Object> pesquisarPrompts() {
        return ResponseEntity.status(HttpStatus.OK).body(promptService.findAll());
    }

    /**
     * Endpoint que inicia a geração de um prompt para o usuário
     */
    @GetMapping ("/gerarprompt")
    public ResponseEntity<Object> gerarPrompt() {
        FakeRequisitosDTO localRequisitos = new FakeRequisitosDTO();
         // construtor de prompt
        return ResponseEntity.status(HttpStatus.OK).body(construtorPrompt(localRequisitos));
    }

    /**
     * Método que pesquisa todos os prompts de um usuário
     * e realiza a construção do prompt final para o usuário
     * e consequentemente a sua utilização no chatGPT
     * @return ResponseEntity<Object>
     * TODO: implementar a lógica de construção do prompt final em métodos ao invés de ser dentro do switch
     *     - para diminuir o acoplamento e aumentar a coesão
     */
    public String construtorPrompt (FakeRequisitosDTO requisitos) {

        StringBuilder promptFinal = new StringBuilder();
        //treinamento prévio do ChatGPT
        //treinar o chatGPT com padrões, se provou eficiente
        promptFinal.append("ChatGPT a partir de agora você vai simular que é um construtor de páginas em" +
                " HTML, irá manter esse dicionário de prompts chave para a construção de um exemplo de página" +
                " HTML começando por <body></body>. Não crie nada que eu não peça.\n" +
                "Armazene as seguintes instruções abaixo\n" +
                "quando aparecer escrito 'crie um bloco container' escreva na página HTML modelo uma " +
                "<div class=\"container\"></div>.\n" +
                "quando aparecer escrito 'crie um bloco cabeçalho'  escreva na página html modelo uma " +
                "<div id=\"titulo\"class=\"header\"></div> dentro desse bloco quando eu escrever 'crie uma etiqueta" +
                " com o identificador'," +
                " você deverá criar <label>e dentro desse label irei definir na frase na hora</label>.\n" +
                "quando aparecer escrito 'crie um bloco padrão' escreva na página html  " +
                "<div class=\"block block-one-columns\"></div>.\n" +
                "quando aparecer escrito 'crie um bloco com duas colunas' escreva na página html " +
                "<div class=\"block block-two-columns\"></div>.\n" +
                "quando aparecer escrito 'crie um bloco com três colunas' escreva na página html  " +
                "<div class=\"block block-three-columns\"></div>.\n" +
                "quando aparecer escrito 'crie um bloco com quatro colunas' escreva na página html  " +
                "<div class=\"block block-four-columns\"></div>.\n" +
                "quando aparecer escrito 'crie um bloco cinco colunas' escreva na página html  " +
                "<div class=\"block block-five-columns\"></div>.\n" +
                "quando aparecer escrito 'crie um bloco seis colunas' escreva na página html  " +
                "<div class=\"block block-six-columns\"></div>.\n" +
                "quando aparecer escrito Crie um bloco declarando a classe button-container" +
                "e button-container-align-center, todos os botões devem estar no mesmo bloco, " +
                "não crie em bloco separados\n\n");

        //instruções de criação de blocos
        promptFinal.append("Crie um bloco declarando a classe container, a partir daqui todos os blocos" +
                " serão criados dentro de container.\n");

        //instruções de criação do header sempre será fixo
        promptFinal.append("Crie um bloco declarando a classe header, com o <label>")
                .append(requisitos.getNome()).append("</label>.\n");

        //colocando os dados em ordem de bloco crescente
        Comparator<FakeDadosReqDTO> fakeDadosReqDTOComparator = Comparator.comparingInt(FakeDadosReqDTO::getBloco);
        Arrays.sort(requisitos.getListaDadosRequisitos().toArray(new FakeDadosReqDTO[0]), fakeDadosReqDTOComparator);

        //instruções de criação de blocos a partir dos dados
        //mantém todos os dados do mesmo bloco juntos
        int contadorDadosPorBloco = 1;
        //contador de colunas para cada bloco de dados do requisito vai de 1 a 6 define qual classe CSS será utilizada
        //é enviado para o método quantidadeColunas
        int contadorColunas = 0;
        //verifica se já foi verificado as colunas do bloco e a introdução do bloco já foi feita
        //se sim não é necessário fazer novamente, até que o próximo dado de bloco seja diferente
        boolean verificouColunasDoBloco = false;

        for (int i = 0; i < requisitos.getListaDadosRequisitos().size(); i++) {
            FakeDadosReqDTO dadoRequisito = requisitos.getListaDadosRequisitos().get(i);
            //Verifica se o bloco é diferente do anterior e se for,
            // incrementa o contador de blocos e seta a variável de verificação para false
            if(dadoRequisito.getBloco() != contadorDadosPorBloco) {
                contadorDadosPorBloco = dadoRequisito.getBloco();
                verificouColunasDoBloco = false;
                contadorColunas = 0;
                promptFinal.append("\n");
            }
            //prompt padrão para criação de dados
            //cada tipo de dado tem um prompt diferente, por isso o switch
            //primeiro é verificado se a intro do bloco já foi informado para que não tenha repetição
            //segundo é consutrido o prompt do bloco com os dados constantes no DadosRequisitosDTO
            switch (dadoRequisito.getTipo()) {
                case "Texto" :
                    if(!verificouColunasDoBloco) {
                            // Intro do bloco de texto do prompt
                            promptFinal.append("Crie um bloco declarando a classe ")
                                    .append(quantidadeColunas(dadoRequisito.getColuna())).append(", ");
                            verificouColunasDoBloco = true;
                    }
                    promptFinal.append(colunas[contadorColunas] + "<div> declare o identificador com o <label>"
                            + dadoRequisito.getDescricao() + "</label>," +
                            " e o input de entradas dados correspondente</div>"
                            + dadoRequisito.getInformacoesAdicionais() + "\n");
                    break;
                case "Botão" :
                    if(!verificouColunasDoBloco) {
                        // Intro do bloco de texto do prompt
                        promptFinal.append("Crie um bloco declarando a classe button-container " +
                                    " e button-container-align-center, ");
                            verificouColunasDoBloco = true;
                    }
                        promptFinal.append("Crie um botão declarando a classe button "
                            + dadoRequisito.getInformacoesAdicionais() + "\n");
                    break;
                case "Radio":
                    if(!verificouColunasDoBloco) {
                        // Intro do bloco de radio button do prompt
                        promptFinal.append("Crie um bloco declarando a classe block-six-columns, e dentro dele ");
                    }
                        promptFinal.append("<div class=\"check\"> declare o identificador com o <label>"
                        + dadoRequisito.getDescricao() + "</label>,"
                        + dadoRequisito.getInformacoesAdicionais() +
                        "</div>\n");
                    break;
                case "Radio V":
                    // TODO - implementação futura de radio button vertical
                    break;
                case "Check":
                    if(!verificouColunasDoBloco) {
                        // Intro do bloco de check do prompt
                        promptFinal.append("Crie um bloco declarando a classe " +
                                "block-six-columns-checkbox-horizontal, e dentro dele ");
                    }
                    promptFinal.append(colunas[contadorColunas] + "<div> declare o identificador com o <label>"
                            + dadoRequisito.getDescricao() + "</label>," +
                            " e o input de entradas dados correspondente</div> e um <label class=\"checkbox-label\">" +
                            "para cada opção mencinando a seguir "
                            + dadoRequisito.getInformacoesAdicionais() + "\n");
                    break;
                case "Check V":
                    // TODO - implementação futura de check box vertical
                    break;
                case "Tabela":
                    if(!verificouColunasDoBloco) {
                        // Intro do bloco de check do prompt
                        promptFinal.append("Crie um bloco declarando a classe " +
                                "block-one-columns, e dentro dele ");
                    }
                    promptFinal.append("declare o identificador com o <label>"
                            + dadoRequisito.getDescricao() + "</label>," +
                            " e uma tabela de entradas dados correspondentes "
                            + dadoRequisito.getInformacoesAdicionais() + "\n");
                    break;
                case "Select" :
                    promptFinal.append(colunas[contadorColunas] + "<div> declare o identificador com o <label>"
                            + dadoRequisito.getDescricao() + "</label>," +
                            " e o input de entradas dados correspondente</div>"
                            + dadoRequisito.getInformacoesAdicionais() + "\n");
                    break;
                default:
                    System.out.println("Houve um erro na geração do prompt");
                    break;
            }
            contadorColunas++;
        }
        promptFinal.append(" \n");
        promptFinal.append("Crie um bloco declarando a classe footer com o <label>Protótio de página HTML gerado " +
                "automaticamente com auxílio do ChatGPT</label>.\n");
        promptFinal.append("Não crie nenhum código CSS. Não é necessário gerar mais o código Json, gere somente o HTML.\n");
        return promptFinal.toString();
    }

    @GetMapping("/listar-requisitos")
    public FakeRequisitosDTO carregarRequisitos() {
        FakeRequisitosDTO requisitos = new FakeRequisitosDTO();
        Comparator<FakeDadosReqDTO> fakeDadosReqDTOComparator = Comparator.comparingInt(FakeDadosReqDTO::getBloco);
        Arrays.sort(requisitos.getListaDadosRequisitos().toArray(new FakeDadosReqDTO[0]), fakeDadosReqDTOComparator);

        return ResponseEntity.status(HttpStatus.OK).body(requisitos).getBody();
    }

    @PostMapping("/prototipo")
    public ResponseEntity<Object> exibirPrototipo(@RequestBody String html) {

        String htmlChatGPT = html.replaceAll("[{}\\\\]", "");
        int indiceInicial = htmlChatGPT.indexOf(palavraToken1) + palavraToken1.length();
        int indiceFinal = htmlChatGPT.indexOf(palavraToken2);
        String fragmento = htmlChatGPT.substring(indiceInicial, indiceFinal);
        String htmlModeloFinal = htmlModelo.replace(marcador, fragmento);

        return ResponseEntity.ok().body(htmlModeloFinal);
    }

    /**
     * Método que retorna a quantidade de colunas GridCSS de um bloco
     * @param tipoBloco
     * @return String
     */
    public String quantidadeColunas(int tipoBloco) {
        switch (tipoBloco) {
            case 1:
                return "block block-one-columns";
            case 2:
                return "block block-two-columns";
            case 3:
                return "block block-three-columns";
            case 4:
                return "block block-four-columns";
            case 5:
                return "block block-five-columns";
            case 6:
                return "block block-six-columns";
            default:
                return null;
        }
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }
}