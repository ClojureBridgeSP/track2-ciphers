(ns cipher.core-test
  (:require [midje.sweet :refer :all]
            [cipher.core :as core]))


(def encr1 "radyjgtxhpsncpbxrvtctgpaejgedhtegdvgpbbxcvapcvjpvtrdbqxcxcv
iwtpeegdprwpqxaxinpcsxcitgprixktstktadebtciduphrgxeixcvapcvjp
vtlxiwpctuuxrxtcipcsgdqjhixcugphigjrijgtudgbjaixiwgtpstsegdvg
pbbxcvo")


(facts "to-int"
        (core/to-int \c) => 2)

(facts "adicionar"
       (core/adicionar 23 3) => 26)

(facts "mod"
       (mod 26 26) => 0)

(facts "shift"
       (core/shift \a 3) => \d
       (core/shift \b 20) => \v
       (core/shift \x 3) => \a
       (core/shift \d -3) => \a)

(tabular
       (fact "caesar-encrypt"
             (core/caesar-encrypt ?w ?k) => ?expect)
       ?w ?k ?expect
       "apple" 20 "ujjfy"
       "leticia" 3 "ohwlfld")

(tabular
  (fact "caesar-dencrypt"
        (core/caesar-dencrypt ?w ?k) => ?expect)
  ?w ?k ?expect
  "ujjfy" 20 "apple"
  "ohwlfld" 3 "leticia")

(tabular
  (fact "get-letters"
        (core/get-letters ?w) => ?expect)
  ?w ?expect
  "Oii, funcionou!" "oiifuncionou")

(tabular
  (fact "encriptar"
        (core/encriptar ?w ?k) => ?expect)
  ?w ?k ?expect
  "Hello, friend!" 5 "mjqqtkwnjsi")

(tabular
  (fact "count-letters"
        (core/count-letters ?letra ?w) => ?expect)
  ?letra ?w ?expect
  \a "aadvark" 3
  \x "aadvark" 0
  \d "oiii" 0)

(facts "to-char"
       (core/to-char 0) => \a)

(tabular
  (fact "get-tres-primeiros"
        (core/get-tres-primeiros ?w) => ?expect)
  ?w ?expect
  "aabbbc" '([\b 3] [\a 2] [\c 1])
  encr1 '([\p 19] [\t 17] [\x 17]))

(tabular
  (fact "computar-chave"
        (core/computar-chave ?a ?b) >= ?expect)
  ?a ?b ?expect
  \p \e 11
  \p \t -4
  \p \a 15)

(tabular
  (fact "descriptografar"
        (core/descriptografar ?a ?b) >= ?expect)
  ?a ?b ?expect
  encr1 \e "xgjepmzdnvytivhdxbzizmvgkpmkjnzkmjbmvhhdibgvibpvbzxjhwdidibxoczvkkmjvxcvwdgdotviydiozmvxodqzyzqzgjkhziojavnxmdkodibgvibpvxbzrdocvizaadxdzioviymjwpnodiamvnompxopmzajmhpgodocmzvyzykmjbmxvhhdibu"
  encr1 \t "vehcnkxbltwrgtfbvzxgxkteinkihlxikhzktffbgzetgzntzxvhfubgbgzvmaxtiikhtvatubebmrtgwbgmxktvmboxwxoxehifxgmhytlvkbimbgzetgzntvzxpbmatgxyybvbxgmtgwkhunlmbgyktlmknvmnkxyhkfnembmakxtwxwikhzkvtffbgzs"
  encr1 \a "clojureisadynamicgeneralpurposeprogramminglanguagecombiningctheapproachabilityandinteractivedevelopmentofascriptinglanguacgewithanefficientandrobustinfrastructureformultithreadedprogrcammingz"
  "ahixblmaxmabgzpbmayxtmaxklmatmixkvaxlbgmaxlhnetgwlbgzlmaxmngxpbmahnmmaxphkwltgwgxoxklmhiltmtee" \t "hopeisthethingwithfeathersthatperchesinthesoulandsingsthetunewithoutthewordsandneverstopsatall")


(tabular
  (fact "encrypt-letter"
        (core/encrypt-letter ?a ?b) >= ?expect)
  ?a ?b ?expect
  \e \i \m
  \u \i \c)
