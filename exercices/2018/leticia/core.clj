(ns cipher.core)




(def encr1 "radyjgtxhpsncpbxrvtctgpaejgedhtegdvgpbbxcvapcvjpvtrdbqxcxcv
iwtpeegdprwpqxaxinpcsxcitgprixktstktadebtciduphrgxeixcvapcvjp
vtlxiwpctuuxrxtcipcsgdqjhixcugphigjrijgtudgbjaixiwgtpstsegdvg
pbbxcvo")


(defn to-int
  [letra]
  (- (int letra) (int \a)))

(defn adicionar
  [valor chave]
  (+ valor chave))

(defn novo-valor
  [posicao]
  (char (+ posicao (int \a))))

(defn shift
  [letra chave]
  (-> (to-int letra)
      (adicionar chave)
      (mod 26)
      novo-valor))

(defn caesar-encrypt
  "encriptando uma palavra w com uma chave k utilizanado a cifra de César"
  [w k]
  (apply str (mapv #(shift % k) w))
  )

(defn caesar-dencrypt
  "decriptação uma palavra w com uma chave k utilizanado a cifra de César"
  [w k]
  (apply str (mapv #(shift % (- k)) w))
  )


(defn get-letters
  [vstr]
  (->> (filterv #(Character/isLetter %) vstr)
      (apply str)
      clojure.string/lower-case))

(defn encriptar
  [w k]
  (-> (get-letters w)
      (caesar-encrypt k)))

(defn count-letters
  [letra vstr]
  (->> (filterv #(= % letra) vstr)
       count))

(defn to-char
  [letra]
  (char (+ letra (int \a))))

(def alphabet (map to-char (range 26)))

(defn contar-letras
  [frase]
  (->> (map #(count-letters % frase) alphabet)
      (zipmap alphabet)))

(defn ordenar-hashmap
  [hashmap]
  (sort-by second > hashmap))

(defn get-tres-primeiros
  [frase]
  (->> (contar-letras frase)
      (ordenar-hashmap)
      (take 3)))

(defn computar-chave
  [c2 c1]
  (- (int c2) (int c1)))

(defn descriptografar
  [frase letra]
  (-> (get-tres-primeiros frase)
      (nth 0)
      (get 0)
      (computar-chave letra)
      (->> (caesar-dencrypt frase))))


(defn encrypt-letter
  [c1 c2]
  (-> (+ (to-int c1) (to-int c2))
      (mod 26)
      novo-valor))

(def cycle1 (cycle "cipher"))

(defn encriptar-Vigenere
  []
  (mapv #()))