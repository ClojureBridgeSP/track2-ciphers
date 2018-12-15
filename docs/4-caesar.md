# [Cifra de César](https://pt.wikipedia.org/wiki/Cifra_de_C%C3%A9sar)

## Substituição de letras
*Funções relevantes:* [+](https://clojuredocs.org/clojure.core/%2B), [-](https://clojuredocs.org/clojure.core/-), [mod](https://clojuredocs.org/clojure.core/mod)

Cifra de César funciona deslocando o alfabeto em um número de posições para esquerda, retornando para o início ao chegar no limite de caracteres, formando um ciclo.
A chave para a cifra é o número de posições que as letras são movidas. Por exemplo, se a chave é 3 então `a` é substituido por `d`, `b` por `e` etc. Aqui está a substituição do alfabeto inteiro:
```
abcdefghijklmnopqrstuvwxyz
defghijklmnopqrstuvwxyzabc
```

Para escrever a função `shift` que move uma letra em um dado número de posições, nós precisamos:

1. Converter a letra para um inteiro utilizando a função `to-int`;
2. Adicionar `n` a ele;
3. Pegue o resultado módulo 26 (que nos permitirá voltar ao início). Por exemplo, se a letra é `x` (posição 23 do alfabeto, onde `a` é 0), e iremos mover 3 posições, então o resultado seria 23 + 3 = 26. A maior letra é `z`, na posição 25, então 26 deveria resultar em 0. Utilizar o resultado módulo 26 realiza essa tarefa;
4. Depois de computar a posição, nós precisamos converte-la de volta para um caractere aplicando a função `to-char` que você escreveu anteriormente.

A função que realiza o módulo aritmético é `mod`. Aqui há alguns exemplos de como ela funciona:
```clojure
(mod 7 26) ; resultado: 7
(mod 27 26) ; resultado: 1
(mod 55 26) ; resultado: 3
(mod -5 26) ; resultado: 21
```

**Exercício:** Escreva uma função `shift` de acordo com a descrição acima. Alguns exemplos de como ela deveria funcionar:

```clojure
(shift \a 3) ; resulta \d
(shift \b 20) ; resulta \v
(shift \z 3) ; resulta \c
```

Nota: se você move com um número negativo, você está realizando a operação reversa. Por exemplo, `(shift \d -3)` te dá `\a`. Portanto a decriptação é apenas utilizar a mesma função, mas com uma chave oposta (negativa).

## Trabalhando com palavras: sequências, `map`, `mapv`
*Funções relevantes:* [map](https://clojuredocs.org/clojure.core/map), [mapv](https://clojuredocs.org/clojure.core/mapv)

Agora você pode "encriptar" uma letra, mas você provavelmente quer encriptar palavras. Se você estivesse escrevendo um programa em Python ou Java, você provavelmente estaria pensando em um *loop*. No entanto, em Clojure, nós utilizaremos *funções de alta ordem* que atravessam sequências para nós, e nós apenas precisamos especificar que operação nós queremos realizar em cada elemento.

`map` e `mapv` são as tais funções de alta ordem. Eles recebem uma sequência de elementos e uma função, e retornam uma sequência que resulta de aplicar a função dada a cada elemento.

Isso pode soar muito abstrato, então vamos olhar um exemplo com `mapv`. Nós iremos utilizar a função `inc` (incremento)  que recebe um inteiro e retorna o próximo inteiro, i.e. `(inc 1)` retorna 2. Agora nós iremos incrementar cada elemento de uma sequência de números utilizando `mapv`:

```clojure
(mapv inc [1 3 2]) ; retorna [2 4 3]
```

Aqui `[1 3 2]` e `[2 4 3]` são *vetores* de números. Esta é a maneira mais fácil de utilizar em Clojure uma coleção de elementos em uma ordem específica.  O que você recebe de volta é um vetor que cada elemento do vetor dado é incrementado em 1.

A diferença entre `map` e `mapv` é que eles retornam o resultado de uma maneira levemente diferente: `map` retorna uma sequência na sua forma mais geral, e `mapv` retorna o resultado como uma coleção sequencial conhecida como um *vetor*. Vetores são levemente mais fáceis de trabalhar para nossos exemplos, então nós iremos utilizar `mapv`.

**Exercício:**  O que você espera quando você escreve no Clojure REPL?

```clojure
(mapv to-int [\a \b \c])
```
Teste, veja se o resultado é o que você está esperando. Se não for, tenha certeza de entender o que é e o porque.

Note que você também pode aplicar `mapv` a uma string:
```clojure
(mapv to-int "abc")
```
O resultado é um vetor de números.

### Utilizando funções anônimas
*Maps* são normalmente utilizados junto com funções anônimas. Estas são funções que você utiliza uma vez que são juntas na hora e não tem um nome. Elas também não dão nomes aos seus parâmetros, referenciando-os como `%1, %2, %3` - ou apenas `%` se existe apenas um.

Elas são normalmente utilizadas com funções de alta ordem, como `mapv`. Aqui há um exemplo:
```clojure
(mapv #(* % %) [1 3 -2])
```
Isso retorna `[1 9 4]` (o vetor de quadrados de todos os números dados, assim como no exercício acima).
A função anônima passada para o `map` é `#(* % %)`. É equivalente a função quadrática. O sinal `%` aqui referencia ao parâmetro da função, ela é utilizada no lugar do `x`. O `#` na frente da expressão indica que é uma função.

**Exercício:** Utilize `mapv` e uma função anônima para retornar o oposto de cada número dado em um vetor. Por exemplo, se o vetor é`[2 -1 0 3]`, o resultado seria `[-2 1 0 -3]`.

### Convertendo de vetores para strings

`mapv` retorna seu resultado como um vetor, mas seria bem útil tê-lo como uma string. A conversão não é óbvia, e você pode pular as explicações de como isso funciona. Aqui está o código para isso:
```clojure
(apply str [\w \o \r \d]) ; resulta em uma string "word"
```

**Explicações do `apply` (você pode pular isso):**

Aqui está a [descrição do clojuredocs sobre `apply`](https://clojuredocs.org/clojure.core/apply). A função recebe um vetor e passa os valores individuais para a função, como se elas fossem escritas separadamente, e não em um vetor. Por exemplo, `(+ [1 2 3])` retorna um erro já que `[1 2 3]` é um vetor, e `+` não funciona com vetores. O que nós queremos é `(+ 1 2 3)`, que é uma soma válida e retorna `6`. Utilizando `(apply + [1 2 3])` funciona exatamente assim: ele passa os três argumentos para `+` individualmente, não como em um vetor.

Agora terminamos com os detalhes mais importantes para as nossas cifras, e estamos prontos para fazermos alguma encriptação e decriptação.

### Encriptando com as cifras de César

Agora nós podemos encriptar palavras com a cifra de César. Vamos dizer que queremos encriptar a palavra "apple" movendo o alfabeto em 20 posições. Nós precisamos fazer os seguintes passos:

1. Utilize `mapv` para mover cada letra em uma sequência de 20 posições; nós podemos escrever a mudança de posição como uma função anônima que utiliza a função `shift` que nós escrevemos anteriormente;
2. Utilize `apply str` para converter o resultado de uma sequência para uma string.

Sinta-se livre para escrever isso em um papel ou em um editor de texto antes de olhar para a solução abaixo.

```clojure
(def s (mapv #(shift % 20) s)) ; encripta a sequência
(def result (apply str s)) ; converte para uma string
```
O resultado `"ujjfy"`, é a encriptação de "apple" com uma chave `20`.

Ao invés de salvar os resultados intermediários em variáveis, você pode escrever todos os passos em uma linha de código:

```clojure
(apply str (mapv #(shift % 20) "apple"))
```
Este último estilo é mais comum em Clojure.

Naturalmente, nós queremos encriptar palavras diferentes, não apenas "apple", e utilizar chaves que não sejam `20`. Deste modo, nós queremos escrever uma função que recebe uma palavra e um número `k`, e move a palavra em `k`. Aqui `k` serve como uma chave para a cifra.

**Exercício:** Abaixo está o começo da função que encripta uma palavra `w` com uma chave `k`. Preencha o corpo da função e teste com alguns exemplos.
```clojure
(defn caesar-encrypt
  "encriptando uma palavra w com uma chave k utilizanado a cifra de César"
  [w k]
                                     )
```

Não se esqueça de escrever todas as funções em um editor de texto, salvar e testar a função no REPL.

Tenha certeza que `(caesar-encrypt "apple" 20)` retorna o mesmo resultado que a expressão que você escreveu antes, e que passando diferentes palavras (todas em letra minúscula, sem espaços) e diferentes chaves te dá encriptaçõs diferentes.

## Decriptando com a cifra de César
Encriptação é boa apenas se nós podemos decriptar o texto.

**Exercício:** Baseado na função `caesar-encrypt`, escreva uma função `caesar-decrypt` que recebe uma palavra encriptada (toda em letra miníscula, sem espaçcos e outros símbolos) e uma chave, e retorna sua encriptação. Lembre-se que a gente pode utilizar a mesma função `shift` para decriptação.

Teste que `(caesar-decrypt "ujjfy" 20)` retorna `"apple"`.

Então tente decriptar o seguinte:

- `(caesar-decrypt "gtxyts" 5)`
- `(caesar-decrypt "mvytebolbsnqo" 10)`

**Exercício:** Encripte seus próprios exemplos e os poste no slack (com a chave), e então tente decriptar os exemplos de outros participantes postados lá. Antes de postar o seu próprio, tenha certeza que eles decriptam corretamente.

## Trabalhando com strings que possuem outros símbolos

Encriptação não é particulamente útil se ela preserva letras maiúsculas, pontuação, espaços entre as palavras, e coisas similares que revelam muito sobre o texto. Portanto, para encriptar o texto nós iremos remover todos os símbolos que não sejam letras e iremos convertes as letras em minúsculas.

### Convertendo para letras minúsculas
```clojure
(clojure.string/lower-case "What is Clojure?") ; resulta em "what is clojure?"
```

### Removendo os símbolos que não são letras
*Funções relevantes no clojuredocs:* [filter](https://clojuredocs.org/clojure.core/filter), [filterv](https://clojuredocs.org/clojure.core/filterv), [odd?](https://clojuredocs.org/clojure.core/odd_q)
<br />
*Função Java relevante:* [isLetter](https://docs.oracle.com/javase/8/docs/api/java/lang/Character.html#isLetter-char-)

Agora iremos utilizar outra função de alta ordem, `filterv`, para remover todos os caracteres que não são letras de uma string. Ela recebe uma função que retorna um valor `true` ou `false` e um vetor, e retorna um novo vetor somente com aqueles elementos que a função passada retornou `true`.

Por exemplo, se nós quisermos utilizar a função `odd?` (ímpar) que funciona assim: `odd? 5` retorna `true`, `odd? 4` retorna `false`. Se nós quisermos manter apenas os inteiros ímpares de uma dada sequência, nós podemos utilizar `filterv` com `odd?`.

```clojure
filterv odd? [6 7 -1 0 5]) ; resulta em [7 -1 5]
```

Note que `filterv` é um vetor analógico de uma função mais comum (mas menos conveniente no nosso caso) `filter`, assim como `mapv` é um vetor analógico de `map`.

Assim como `mapv`, `filterv` também pode receber funções anônimas:
```clojure
(filterv #(< % 5) [3 6 5 8 0]) ; resulta em [3 0]
```

A função anônima `#(< % 5)` retorna verdadeiro se o argumento é menor ou igual a `5` e falso ao contrário.

Nós iremos utilizar o método Java da classe `Character` para checar se o caractere é uma letra. Existem algumas diferenças leves em como este método é definido em Java: não é um método que está relacionado a nenhum objeto, como na classe `Character` (é então chamado um método estático), e a sintaxe para chamá-lo é um pouco diferente:
```clojure
(Character/isLetter \a) ; true
(Character/isLetter \?) ; false
```

**Exercício:** escreva uma função `get-letters` que recebe uma string com qualquer símbolo nela, e retorna uma string contendo apenas letras, onde todas as letras são convertidas para minúsculas, como no exemplo abaixo:
```clojure
(get-letters "Hello, friend!") ; "hellofriend"
```
A sequência de passos que você precisa reproduzir é:

1. Converta a string para letras minúsculas utilizando `clojure.string/lower-case` (nota: esta função funciona apenas em uma string);
2. Filtre todos os caracteres que não são letras utilizando `filterv`;
3. Converta o resultado de volta para uma string utilizando `apply str`;

Você pode querer primeiramente tentar os passos no REPL, e então juntá-los em uma função.

## Encriptando e decriptando texto com a cifra de César
Agora você está pronta para fazer a encriptação e decriptação com a cifra de César em strings inteiras de texto. O resultado será tudo em letras minúsculas e sem sinais de pontuação, mas ainda estará legível.

A sequência de passos para encriptação irá requerer que você:

1. Utilize `get-letters` para pegar uma string somente com letras (minúsculas) do texto que você está tentando encriptar;
2. Encripte esta string utilizando sua função `caesar-encrypt`.

Como um exemplo de teste, `"Hello, friend!"` com a chave 5 encripta para `"mjqqtkwnjsi"`.

Decriptar não requer filtrar os símbolos e convertê-los para minúsculos já que as strings encriptadas já estão no formato correto, então você pode utilizar a função `caesar-decrypt`.

Tente decriptografar o seguinte:

`whqsuxeffuhmqieduevjxuvyhijfhewhqccuhievjxuxqhlqhtcqhaedusecfkjuhqdtydludjutjxuvyhijsecfybuhvehqsecfkjuhfhewhqccydwbqdwkqwuixufefkbqhyputjxuytuqevcqsxyduydtufudtudjfhewhqccydwbqdwkqwuimxysxbutjejxutulubefcudjevserebeduevjxuvyhijxywxbulubfhewhqccydwbqdwkqwuiemydwjexuhqssecfbyixcudjiqdtxuhdqlqbhqdaixumqiiecujycuihuvuhhutjeqiqcqpydwwhqsujxukidqloqhbuywxrkhausbqiiwkytutcyiiybutuijheouhkiixeffuhmqidqcutvehxuhixumqifeijxkcekiboqmqhtutjxufhuiytudjyqbcutqbevvhuutecrofhuiytudjrqhqsaerqcq` 

Dica: a chave é a resposta para a vida, o universo e tudo mais ;)
 

## E agora?
Encriptação e decriptação são fáceis de fazer se você sabe a chave (a quantidadade que irá mover no alfabeto). Mas e o que fazer se você não sabe? A próxima seção mostra como você pode quebrar a cifra de César sem uma chave utilizando *hashmaps* em Clojure.

**Next:** [Breaking Caesar cipher: hashmaps](5-frequency.md)
<br />
**Previous:** [Tipos de dados e funções em Clojure](3-functions.md)

