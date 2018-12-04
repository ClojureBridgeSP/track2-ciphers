# Visão Geral de Clojure.

## Principles of functional languages

Clojure é uma linguagem funcional. Isso quer dizer que, ao invés de atualizar valores em memória, os programas funcionam através de composição de funções. Funções em uma linguagem funcional são tratadas do mesmo jeito que outros elementos da linguagem, como números, cadeidas de caracteres ("strings"), etc. Isto é, elas podem ser guardadas numa variável, passadas para outras funções, retornadas por outras funções e até criadas em tempo de execução. Frequentemente, utilizamos a expressão *funções são cidadãs de primeira classe na linguagem* para descrever esse fato.

Variáveis e estruturas de dados em Clojre são imutáveis por padrão, isto é, elas nunca são alteradas localmente. Sempre que uma modificação é necessária, um novo objeto é criado. Por exemplo, se você adiciona um elemento a uma lista, uma nova lista é criada com o novo elemento adicionado e a lista antiga continua do mesmo jeito.

Isso pode parecer uma abordagem ineficiente, mas por baixo disso há um compartilhamento bem eficiente das partes que não mudaram, e isso é invisível para quem programa. Vetores em Clojure são particularmente eficientes nesse sentido.

O benefício da imutabilidade é que é mais fácil saber exatamente o que está acontecendo num programa dado que todos os objetos que o programa referencia são sempre os mesmo de quando foram criados. Isso também facilita incorporar concorrência, dado que é necessário bem menos trabalho para tomar conta de atualizações simultâneas, e consequentemente menos necessidade de de fazer *lock* em objetos.

## Clojure e Java

Clojure compila para bytecode Java e roda sobre a Java Virtual Machine (JVM). Há uma maneira conveniente de chamar métodos e usar bibliotecas de Java em Clojure. Isso significa que Clojure pode usar qualquer funcionalidade pré-existente em Java.

Devido Clojure ser implementado em cima de Java, objetos em Clojre (números, strings, listas, etc) são implementados como tipos de dados de Java. Infelizmente isso também quer dizer que as mensagens de erro de Clojure são exceções em Java e referem-se a tipos de dados de Java. Isso as torna menos compreensíveis para quem não tem familiaridade com Java. Vamos tentar ajudar você a navegar pelas mensagens de erro se elas se tornarem confusas.

## Sintaxe de Clojure 

Clojure é uma linguagem da familia Lisp. Linguagens Lisp são funcionais, dinamicamente tipada e com uma sintaxe simples e uniforme. Em linguagens Lisp existe algumas poucas palavras-chave (como `def` em Clojure, para definir uma variável) e nenhuma operação predefinida: atém operações aritiméticas como `+` e `-` são funções em Clojure.

A sintaxe de Clojure segue a notação prefixada: uma expressão em Clojure é cercada por parênteses, o primeiro elemento dentro dos parênteses é o nome de uma função, e tudo o que vem depois são os argumentos para esta função:

```clojure
(+ 2 5)
```

é uma chamada para a função `+` com os argumentos `2` e `5`, que retorna `7`.

Várias funções em Clojure podem receber uma quantidade variável de argumentos:

```clojure
(+ 2 5 3)
```

passa três argumentos para `+` e retorna `10`. 

Já que funções em Clojure são cidadãs de primeira classe, elas podem ser passadas para outras funções. Por exemplo:

```clojure
(filter odd? [2 3 -1 8])
```

é uma chamada para a função `filter` que recebe uma função que retorna `true` ou `false` e uma coleção de elementos, e retorna uma nova coleção que contem apenas os elementos que, ao chamar a função passada com eles, ela retorna `true`. Nesse caso, apenas os elementos `3` e `-1` estarão nesta coleção resultante.

Todas as linguagens tem suas convenções de nomenclatura e formação. Clojure (e outras linguagens Lisp) usam a seguinte:

* Nomes de variáveis com múltiplas palavras com hífens, e não underlines ou camel-casing: `find-min-key`.
* Nomes de função constumam ser verbos, não substantivos: `take`, `reduce`, etc.
* Nomes de funções que retornam booleanos (`true`/`false`) terminam com uma interrogação: `odd?`, `number?`. 
* Todos os parênteses fecham na última linha de uma expressão anunhada, como na definição a seguir:
```clojure
(defn square
  [x]
  (* x x))
```

Comentários começam com `;` e vão até o fim da linha:
```clojure
(def n 10) ; n is the number of elements
```
Tradicionalmente usamos `;;` para começar um comentário e ocupa uma linha inteira.

Para mais sobre estilo de Clojure, leia [The Clojure Style Guide](https://github.com/bbatsov/clojure-style-guide) (e você pode revisitar esse link depois que terminar essa lição).

## ClojureDocs

Clojure possui uma excelente documentação que foi montada pelos programadores Clojure: [ClojureDocs: community-powered documentation and examples repository](https://clojuredocs.org/)

Maior parte das funções que você irá utilizar estão listadas no [Guia de referência rápida do ClojureDocs](https://clojuredocs.org/quickref). A lição provê links para as funções utilizadas em cada subseção.

A documentação para cada função vem com uma lista de exemplos, funções relacionadas no fundo da página e até alguns comentários e discussões. Exemplos possuem casos de exemplos mais comuns e alguns casos mais específicos (se aplicáveis).

Nós recomendamos que você tire um tempo para olhar para os primeiros exemplos - e talvez até tentá-los! É bem comum ter várias abas do navegador abertas com descrições de diferentes funções.

ClojureDocs são muito úteis, por favor utilize-os!

## Sobre essa lição

Esta lição foi projetada para ser uma introdução independente ao Clojure utilizando alguns exemplos divertidos de criptografia. Ele irá passar pelas funções mais utilizadas em Clojure, te dará chance de escrever suas próprias funções através de uma série de exercícios sugeridos, e finalmente te dará um exemplo de encriptação para tentar quebrar (i.e. tentar achar a chave e tentar decriptografar) utilizando as ferramentas que você aprendeu.

Apesar dos exemplos serem apenas exemplos de "brincadeira", e não uma encriptação da vida real, as funcionalidades de Clojure e abordagens que você aprenderá serão úteis para qualquer análise e processamento de dados.

Nós te encorajamos a:

1. Utilize esta lição como um guia para o seu aprendizado, não um conjunto rígido de passos. Leve quanto tempo for necessário para explorar o material, você não precisa finalizá-lo ou alcançar algum sprint particular.
2. Experimente as coisas. Clojure REPL é uma boa maneira de brincar com alguns exemplos.
3. Faça perguntas. Mentores estão aí para responder suas perguntar ou explicar resultados que possam parecer confusos ou discutir diferentes abordagens para o seu problema.
4. Você provavelmente irá querer trabalhar em pares, especialmente nas partes posteriores da lição.

**Próximo:** [Tipos de dados e funções em Clojure](3-functions.md)
<br />
**Anterior:** [Setup do Projeto](1-setup.md)
