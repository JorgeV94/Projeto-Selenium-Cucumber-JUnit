# language: pt
Funcionalidade: Navegar e pesquisar no site da BBC

  Contexto: 
    Dado usuario acesse a página principal

  Cenário: Verificar resultados de pesquisa desmatamento
    E clica na aba Brazil
    Quando digita desmatamento no campo pesquisa
    Então deve ver que a mensagem contém: Busca para desmatamento produziu

  Cenário: Abrir a página do Facebook da BBC Brasil e fazer validacão
    Quando clico em Facebook na pagina da BBC
    Então devo ver que a nova página contém BBC News Brasil.

  Cenário: Fazer validação de uma pesquisa no site BBC
    E clico na aba Aprenda Inglês
    Quando digito no campo pesquisa O robô com sensores nos dedos que separa lixo reciclavel
    Então deve ver que a mensagem contém Busca para pesquisa acima produziu 1 resultados.

  Esquema do Cenário: Fazer a validação de pesquisas no site BBC e abrir os links correspondentes
    E clico na aba Aprenda Inglês
    E pesquiso no campo de pesquisa <titulo>
    Quando clico no link
    Então uma nova página abrirá e devo ver o título <newTitle>

    Exemplos: 
      | titulo                                                        | newTitle                                                                        |
      | "O robô com sensores nos dedos que separa lixo reciclavel"    | "O robô com sensores nos dedos que separa lixo reciclável - BBC News Brasil"    |
      | "Como fazer um safári pelo deserto africano sem sair de casa" | "Como fazer um safári pelo deserto africano sem sair de casa - BBC News Brasil" |
      | "Os cisnes que batem na janela pedindo comida"                | "Os cisnes que batem na janela pedindo comida - BBC News Brasil"                |
