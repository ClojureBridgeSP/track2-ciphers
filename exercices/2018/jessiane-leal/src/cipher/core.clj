(ns cipher.core)

;(defn to-int
;  "Converte uma letra em  número"
;  [letra]
;  0)

;(defn to-int
;  "recebe uma letra minúscola e retorna sua posição no alfabeto: a = 0, b = 1, etc."
;  [letter-char]
;  (let [ascii-a (int \a)]
;    (- (int letter-char) ascii-a)))

(defn to-int
  "recebe uma letra minúscola e retorna sua posição no alfabeto: a = 0, b = 1, etc."
  [letter-char]
  (- (int letter-char) (int \a)))

(defn to-char
  "recebe um número de posiçao no alfabeto e retorna uma letra minúscola"
  [n]
  (char (+ n (int \a))))



(defn shift
  "recebe uma letra que vai ser movida de acordo com o n escolhido: a 3 = d"
  [letter-char n]
  (to-char (+ (to-int letter-char) (mod n 26))))

(defn shift-correct
  "recebe uma letra que vai ser movida de acordo com o n escolhido: a 3 = d"
  [letter-char n]
  (to-char (mod(-(to-int letter-char) n) 26)))

(defn shift-cipher
  "recebe uma palavra e retorna a palabvra convertida"
  [word-cipher n]
  (let [char-lower (filterv #(Character/isLetter %) (clojure.string/lower-case word-cipher))]
    (apply str (mapv #(shift % n) char-lower))))

(defn descodific
  "recebe uma palavra e descodifica"
  [word-cipher n]
  (let [char-lower (filterv #(Character/isLetter % ) (clojure.string/lower-case word-cipher))]
    (apply str (mapv #(shift-correct % n) char-lower))))
