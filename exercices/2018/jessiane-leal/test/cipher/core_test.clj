(ns cipher.core-test
  (:require [cipher.core :as core]
            [midje.sweet :refer :all]))

(facts "recebe um caractere e retorna a posicao no alfabeto"

       (fact "o caractere a é a primeira letra, na posição 0"
             (core/to-int \a) => 0)

       (fact "o caractere b é a segunda letra, na posição 1"
             (core/to-int \b) => 1)

       (fact "o caractere j é a décima letra, na posiçao 9"
             (core/to-int \j) => 9))

(facts "recebe um numero de posicao no alfabeto e retorna a letra referente"

       (fact "o caractere a é a primeira letra, na posição 0"
             (core/to-char 0) => \a)

       (fact "o caractere b é a segunda letra, na posição 1"
             (core/to-char 1 ) => \b)

       (fact "o caractere j é a décima letra, na posiçao 9"
             (core/to-char 9) => \j))



(fact "pula letra a n vezes"
      (core/shift \a 26) => \a)

(fact "volta letra n vezes"
      (core/shift-correct \e 4) => \a)

(fact "Recebe palavra e converte de acordo com a chave passada"
      (core/shift-cipher "a" 42) => "q")

(fact "Recebe uma palavra convertida e retorna a palavra real"
      (core/descodific "bcd" 3) => "yza")
