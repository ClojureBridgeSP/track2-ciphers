(ns cipher.core-test
  (:require [cipher.core :as core]
            [midje.sweet :refer :all]))

;(fact "this will fail"
;      1 => 2)
;
;(fact "this will not fail"
;      2 => 2)


(facts "recebe um caractere minúsculo e retorna sua posição no alfabeto: a = 0, b = 1, etc"
       (fact "o caractere a é a primeira letra, na posição 0"
             (core/to-int \a) => 0)
       (fact "o caractere b é a segunda letra, na posição 1"
             (core/to-int \b) => 1))

(facts "recebe um inteiro entre 0 e 25 e retorna seu caracter correspondente no alfabeto"
       (fact "O número 0 é a primeira letra, a letra a"
             (core/to-char 0) => \a)
       (fact "O número 1 é a segunda letra, a letra b"
             (core/to-char 1) => \b)
       (fact "O número 1 é a segunda letra, a letra b"
             (core/to-char 9) => \j))

(facts "tabular - recebe um inteiro entre 0 e 25 e retorna seu caracter correspondente no alfabeto"
       (tabular
         (core/to-char ?int) => ?result
         ?int ?result
         0    \a
         1    \b
         2    \c
         3    \d
         9    \j))

(facts "tabular - recebe um inteiro entre 0 e 25 e retorna seu caracter correspondente no alfabeto"
       (tabular
         (core/shift ?char ?positions) => ?shifted-char
         ?char  ?positions ?shifted-char
         \a     3          \d
         \b     20         \v
         \z     3          \c
         \j     1          \k
         \d     -3         \a
         \z     2          \b))

(fact "encripta uma string utilizando cifra de Cesar segundo um número de posições de deslocamento"
      (tabular
        (core/caesar-encrypt ?text ?key) => ?encrypted-text
        ?text      ?key    ?encrypted-text
        "apple"    20      "ujjfy"
        "carolina" 1       "dbspmjob"))

(fact "decripta uma string utilizando cifra de Cesar segundo um número de posições de deslocamento"
      (tabular
        (core/caesar-decrypt ?encrypted-text ?key) => ?decrypted-text
        ?encrypted-text   ?key    ?decrypted-text
        "ujjfy"           20      "apple"
        "dbspmjob"        1       "carolina"))

(facts "recebe uma string, converte em minúsculo e remove os caracteres não letra"
       (fact "a string contém caracteres especiais e letras maiúsculas que devem ser removidos/convertidos em minúsculo"
             (core/get-letters "Hello, friend!") => "hellofriend"))

(facts "cripografia e decriptografia completas"
       (fact "criptografando texo comum"
             (core/caesar-encrypt "Hello, friend!" 5) => "mjqqtkwnjsi")
       (fact "decriptografando cifra"
             (core/caesar-decrypt "mjqqtkwnjsi" 5) => "hellofriend"))

(fact "conta a ocorrencia de um caracter em uma string"
      (tabular
        (core/count-letters ?char ?text) => ?count
        ?char   ?text      ?count
        \a      "aadvark"  3
        \x      "aadvark"  0
        \i      "tiemi"    2))


(facts "analise de frequencia de textp"
       (core/count-frequences "radyjgtxhpsncpbxrvtctgpaejgedhtegdvgpbbxcvapcvjpvtrdbqxcxcv\niwtpeegdprwpqxaxinpcsxcitgprixktstktadebtciduphrgxeixcvapcvjp\nvtlxiwpctuuxrxtcipcsgdqjhixcugphigjrijgtudgbjaixiwgtpstsegdvg\npbbxcvo" ) => {\a 7, \b 8, \c 16, \d 10, \e 8, \f 0, \g 16, \h 5, \i 13, \j 8, \k 2, \l 1, \m 0, \n 2, \o 1, \p 19, \q 3, \r 8, \s 6, \t 17, \u 5, \v 11, \w 4, \x 17, \y 1, \z 0})

(fact "encontra as três letras mais frequentes de um texto"
      (tabular
        (core/get-most-frequent-letters ?text) => ?map
        ?text                    ?map
        "dddvaaaaaaahrrrtyrgrk"  [[\a 7] [\r 5] [\d 3]]
        "qqqqqqwwwwweeeerrrtty"  [[\q 6] [\w 5] [\e 4]]))

(facts "calcula possivel chave pra uma cifra de Cesar"
       (tabular
         (core/calculate-distance ?char1 ?char2) => ?key
         ?char1  ?char2   ?key
         \p      \e      11
         \r      \b      16
         \c      \g      -4))

(facts "vigenere - encriptacao de um caracter com outro"
       (tabular
         (core/encrypt-letter ?mchar ?kchar) => ?cchar
         ?mchar  ?kchar  ?cchar
         \w      \c      \y
         \u      \i      \c))

(facts "vigenere - decriptacao de um caracter com outro"
       (tabular
         (core/decrypt-letter ?mchar ?kchar) => ?cchar
         ?mchar  ?kchar  ?cchar
         \y      \c      \w
         \c      \i      \u))

(fact "encriptando usando vigenere"
      (core/vigenere-encrypt "welcometoclojurebridge" "cipher") => "ymajsdgbdjpflcglfiklvl")


(fact "decriptando usando vigenere"
      (core/vigenere-decrypt "ymajsdgbdjpflcglfiklvl" "cipher") => "welcometoclojurebridge")

