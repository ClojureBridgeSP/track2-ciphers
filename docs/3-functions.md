# Tipos de dados e funções em Clojure

## Tipos de dados em Clojure

Clojure é uma linguagem funcional que funciona por cima da linguagem de programação Java. Assim, os tipos de dados em Clojure são tipos de dados em Java por debaixo dos panos. Isso provê o conforto de utilizar métodos em Java para trabalhar com os tipos de dados Java, mas também requer conversões para manter a consistência com os tipos de Java.

A criptografia que iremos explorar nessa lição trabalha com letras que são representadas como um caracter do tipo `char`. No entanto, elas são tratadas como números inteiros `int` para fazer a matemática da criptografia funcionar. Letras formam strings, que são um tipo de dado diferente de caracteres.

## Definindo constantes e funções em Clojure

Constantes são declaradas utilizando a palavra reservada `def`:
```clojure
(def answer 42)
```

Funções em Clojure são declaradas como uma expressão entre parênteses que iniciam com a palavra reservada `defn`, seguidas pelo nome da função, um vetor de parâmetros (entre colchetes), e o corpo da função que especifica o que a função retorna. Por exemplo, a seguinte definição


```clojure
(defn f
  [x y]
  (+ x y 100))
```
define uma função `f` que recebe dois parâmetros, `x` e `y`, e retorna a soma deles mais `100`.

Você pode chamar essa função como
```clojure 
(f 2 3)
```

Isso irá retornar `105`.

**Exercício:** Escreva uma função `g` que recebe dois números `x` e `y` e retorna `x` vezes `10` mais `y`.

**Exercício:** Olhe como [`if`](https://clojuredocs.org/clojure.core/if) funciona em clojure e retorne o valor absoluto de `x - y`.

## Conversão entre `int` e `char`

*Funções relevantes no clojuredocs*: [int](https://clojuredocs.org/clojure.core/int), [char](https://clojuredocs.org/clojure.core/char),
[doc](https://clojuredocs.org/clojure.repl/doc)

Agora nós iremos escrever algumas funções de conversão entre os tipos `int` e `char`.

A conversão que nós gostaríamos de realizar é para representar as 26 letras do alfabeto como números `0, 1, 2,..., 25`, onde `a = 0`, `b = 1`, etc.

Caracteres em Clojure são escritos com uma contra barra na frente. Por exemplo, o caractere `x` é escrito como `\x`.

Como os caracteres são condificados pelos seus [valores ASCII](https://en.wikipedia.org/wiki/ASCII#Code_chart), converter um caractere para um `int` retorna seu valor ASCII.
Por exemplo, `(int \c)` é 99.
Código ASCII para letras minúsculas estão em ordem: `(int \a)` é 97, `(int \b)` é 98, etc.
Por causa disso, para converter uma letra para um inteiro entre 0 e 25 de acordo com a sua posição no alfabeto, nós precisamos apenas subtrair dele o valor ASCII de `\a`:

```clojure
(facts "recebe um caractere minúsculo e retorna sua posição no alfabeto: a = 0, b = 1, etc"
  (fact "o caractere a é a primeira letra, na posição 0"
    (core/to-int \a) => 0)
  (fact "o caractere b é a segunda letra, na posição 1"
      (core/to-int \b) => 1))
```

Este teste pode ser melhorado com `tabular`, porque nós provavelmente teremos vários exemplos

```clojure
(facts "recebe um caractere minúsculo e retorna sua posição no alfabeto: a = 0, b = 1, etc"
  (tabular
    (core/to-int ?char) => ?result
    ?char ?result
    \a    0
    \b    1
    \c    2
    \d    3))

```
```clojure
(defn to-int
  "recebe uma letra minúscola e retorna sua posição no alfabeto: a = 0, b = 1, etc."
  [letter-char]
  (let [ascii-a (int \a)]
    (- (int letter-char) ascii-a)))
```

Dê uma olhada na função e tenha certeza de que você entendeu como ela funciona. Teste ela com algumas letras minúsculas.
Também note que nós adicionamos uma descrição para a função, chamada de *doc-string*. Ela fica localizada depois do nome da função e antes de seus parâmetros.
Você pode dar uma olhada na doc-string da função utilizando `(doc function-name)`. Por exemplo, `(doc to-int)` imprime a doc-string para a função `to-int`.

**Exercício:** Escreva a função de conversão reversa `to-char`, i.e. uma função que recebe um número entre 0 e 25 (inclusive) e retorna o caractere minúsculo correspondente. Por exemplo, `(to-char 3)` deveria retornar `\d`.

**Próximo:** [Caesar cipher; Clojure higher order functions](4-caesar.md)
<br />
**Anterior:** [Visão Geral de Clojure](2-functional-overview.md)
