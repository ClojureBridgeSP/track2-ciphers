(ns cipher.core
  (:require [clojure.string :as string])
  (import [java.lang Character]))

(defn to-int
  "Transfroma char to int."
  [letter]
  (let [ascii-a (int \a)]
    (- (int letter) ascii-a)))

(defn to-char
  "Transforma int to char."
  [n]
  (let [ascii-a (int \a)]
    (char (+ n ascii-a))))

(defn shift
  "Modifica a letra de acordo com valor n."
  [letter n]
  (to-char (mod (+ (to-int letter) n) 26)))

(defn get-letters
  "Filtra simbolos e coloca em underscore"
  [word]
  (string/lower-case (apply str (filterv #(Character/isLetter %) word))))

(defn caesar-encrypt
  "Encripta frase secreta por n"
  [text n]
  (apply str (mapv #(shift % n) (get-letters text))))

(defn caesar-decrypt
  "Decripta frase secreta por n"
  [secret n]
  (apply str (mapv #(shift % (* -1 n)) secret)))

(defn increase-frequency
  "Função para aumentar a frequencia da palavra no hash"
  [hash word]
  (assoc hash word (inc (hash word 0))))

(defn build-frequency-map
  "Controi hash de frequencia de todas as palavras da frase"
  [word]
  (reduce increase-frequency {} word))

(defn count-letter
  "Conta letras na frase"
  [chr word]
  (get (build-frequency-map word) chr 0))

(defn most-frequent
  "Pega as tres letras mais frequentes em uma frase"
  [text]
  (take 3 (sort-by second > (build-frequency-map text))))

(defn get-key
  "Pega a diferença entre uma letra e a letra mais frequente no ingles(e, t, a)"
  [word-most-frequent english-most-frequent]
  (mod (- (to-int (char word-most-frequent)) (to-int (char english-most-frequent))) 26))

(defn decrypt-by-key
  "Decripta a partir das letras mais frequentes da frase"
  [word-most-frequent test-english-key secret]
  (let [key (get-key word-most-frequent test-english-key)]
    (println "Key: " key " | Palavra:" word-most-frequent "| No ingles: " test-english-key)
    (println (caesar-decrypt secret key))
    (caesar-decrypt secret key)))

(defn test-keys
  "test keys"
  [secret english-most-frequent]
  (let [frequencies (most-frequent secret)]
    (map #(decrypt-by-key (first %) english-most-frequent secret) frequencies)))

(defn encrypt-letter
  "Encrypta letra com letra"
  [letter letter-of-keyword]
  (to-char (mod (+ (to-int letter) (to-int letter-of-keyword)) 26)))

(defn vigenere-encrypt
  "Encrypta vigenere"
  [text word]
  (let [keyword (take (count text) (cycle word))]
    (apply str (mapv #(encrypt-letter %1 %2) text keyword))
  ))


