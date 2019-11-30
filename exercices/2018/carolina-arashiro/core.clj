(ns cipher.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))


(defn to-int
  "recebe uma letra minúscola e retorna sua posição no alfabeto: a = 0, b = 1, etc."
  [letter-char]
  (let [ascii-a (int \a)]
    (- (int letter-char) ascii-a)))

(defn to-char
  "recebe um inteiro e retorna seu caracter correspondente no alfabeto"
  [int-position]
  (let [ascii-a (int \a)]
    (char (+ ascii-a int-position))))


(defn shift
  "recebe um caracter e uma quantidade de posições e retorna o caracter resultante do deslocamento daquele número de posições no alfabeto"
  [letra posicoes]
  (to-char(mod (+ (to-int letra) posicoes) 26)))

(defn is-letter?
  [char]
  (Character/isLetter char))

(defn get-letters
  "recebe uma string, converte em minúsculo e remove os caracteres não letra"
  [text]
  (apply str (filterv is-letter? (clojure.string/lower-case text))))


(defn caesar-encrypt
  "recebe um texto e uma chave e retorna o texto cifrado utilizando cifra de Cesar"
  [text key]
  (let [letters (get-letters text)]
    (apply str (mapv #(shift % key) letters))))


(defn caesar-decrypt
  "recebe um texto cifrado e uma chave e retorna o texto decifrado utilizando cifra de Cesar"
  [encrypted-text key]
  (caesar-encrypt encrypted-text (- key)))
;; (apply str (mapv #(shift % (- key)) text)))

(defn count-letters
  "recebe um caracter e um texto e retorna a ocorrência"
  [letter text]
  (count (filterv #(= letter %) text)))

(def alphabet (map to-char (range 26)))
;
;(def encr1 "radyjgtxhpsncpbxrvtctgpaejgedhtegdvgpbbxcvapcvjpvtrdbqxcxcv
;iwtpeegdprwpqxaxinpcsxcitgprixktstktadebtciduphrgxeixcvapcvjp
;vtlxiwpctuuxrxtcipcsgdqjhixcugphigjrijgtudgbjaixiwgtpstsegdvg
;pbbxcvo")

(defn count-frequences
  "Conta a frequencia de cada caracter de uma string"
  [text]
  (zipmap alphabet (map #(count-letters % text) alphabet)))

(defn get-most-frequent-letters
  "retorna as três letras mais frequentes em uma distribuição de frequências"
  [text]
  (take 3 (sort-by second > (count-frequences text))))

(defn calculate-distance
  "calcula a distância entre as posições de duas letras"
  [char1 char2]
  (- (to-int char1) (to-int char2)))

(defn vigenere-letter-operation
  [operation mchar kchar]
  (to-char (mod (operation (to-int mchar) (to-int kchar)) 26)))

(defn encrypt-letter
  "cifra um caracter usando vigenere"
  [mchar kchar]
  (vigenere-letter-operation + mchar kchar))

(defn decrypt-letter
  "cifra um caracter usando vigenere"
  [mchar kchar]
  (vigenere-letter-operation - mchar kchar))

(defn vigenere-operation
  [cryption-function text key]
  (apply str (map cryption-function text (take (count text) (cycle key)))))


(defn vigenere-encrypt
  "cifra uma mensagem usando vigenere"
  [text key]
  (vigenere-operation encrypt-letter text key))

(defn vigenere-decrypt
  "decifra uma mensagem usando vigenere"
  [text key]
  (vigenere-operation decrypt-letter text key))