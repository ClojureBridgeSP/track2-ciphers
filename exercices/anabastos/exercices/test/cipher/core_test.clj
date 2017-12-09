(ns cipher.core-test
  (:require [cipher.core :as core]
          [midje.sweet :refer :all]))

(facts "takes a lowercase letter character and returns its position in the alphabet: a = 0, b = 1, etc"
  (tabular
    (core/to-int ?char) => ?result
    ?char ?result
    \a    0
    \b    1
    \c    2
    \d    3))

(facts "takes a num and returns lowercase letter"
  (tabular
    (core/to-char ?n) => ?result
    ?n ?result
    0    \a
    1    \b
    2    \c
    3   \d))

(facts "shifts letter :)"
  (tabular
    (core/shift ?n 3) => ?result
    ?n ?result
    \a    \d
    \b    \e
    \c    \f
    \d   \g))


(fact "shifts alfa"
  (core/caesar-encrypt "abcdefghijklmnopqrstuvwxyz" 3) => "defghijklmnopqrstuvwxyzabc")

(fact "shifts alfa"
  (core/caesar-encrypt "Hello, friend!" 5) => "mjqqtkwnjsi")

(fact "shifts alfa"
  (core/caesar-decrypt "defghijklmnopqrstuvwxyzabc" 3) => "abcdefghijklmnopqrstuvwxyz")

(fact "shifts alfa"
  (core/get-letters "Hello, friend!") => "hellofriend")

(facts 
  (fact "shifts alfa"
    (core/count-letter \a "aadvark") => 3)

  (fact "shifts alfa"
    (core/count-letter \x "aadvark") => 0)
)

(fact "get frequency"
    (core/most-frequent "ahixblmaxmabgzpbmayxtmaxklmatmixkvaxlbgmaxlhnetgwlbgzlmaxmngxpbmahnmmaxphkwltgwgxoxklmhiltmtee") => '([\m 14] [\x 12] [\a 11]))

(facts "get key :)"
  (tabular
    (core/get-key ?n \e) => ?result
    ?n ?result
    \m   8
    \c   24))


(fact "decrypttest"
  (core/decrypt-by-key \j \e "mjqqtkwnjsi") => "hellofriend")

(facts "test keys"
  (tabular
    (core/test-keys "ahixblmaxmabgzpbmayxtmaxklmatmixkvaxlbgmaxlhnetgwlbgzlmaxmngxpbmahnmmaxphkwltgwgxoxklmhiltmtee" ?n) => ?result
    ?n ?result
    \e   '("szaptdespestyrhtesqplespcdesleapcnspdtyespdzfwlyodtyrdespefyphteszfeesphzcodlyoypgpcdezadlelww"
 "hopeisthethingwithfeathersthatperchesinthesoulandsingsthetunewithoutthewordsandneverstopsatall"
 "elmbfpqebqefkdtfqecbxqebopqexqmbozebpfkqebplrixkapfkdpqebqrkbtfqelrqqebtloapxkakbsbopqlmpxqxii")
    \t   '("hopeisthethingwithfeathersthatperchesinthesoulandsingsthetunewithoutthewordsandneverstopsatall"
 "wdetxhiwtiwxcvlxiwutpiwtghiwpietgrwthxciwthdjapcshxcvhiwtijctlxiwdjiiwtldgshpcsctktghidehpipaa"
 "tabqueftqftuzsiuftrqmftqdeftmfbqdotqeuzftqeagxmzpeuzseftqfgzqiuftagfftqiadpemzpzqhqdefabemfmxx")
    \a   '("ovwlpzaolaopundpaomlhaolyzaohawlyjolzpuaolzvbshukzpunzaolabuldpaovbaaoldvykzhukulclyzavwzhahss"
 "dklaeopdapdejcsepdbawpdanopdwplanydaoejpdaokqhwjzoejcopdapqjasepdkqppdasknzowjzjaranopklowpwhh"
 "ahixblmaxmabgzpbmayxtmaxklmatmixkvaxlbgmaxlhnetgwlbgzlmaxmngxpbmahnmmaxphkwltgwgxoxklmhiltmtee")))

(facts "test"
  (fact "decrypts"
    (core/caesar-decrypt "ahixblmaxmabgzpbmayxtmaxklmatmixkvaxlbgmaxlhnetgwlbgzlmaxmngxpbmahnmmaxphkwltgwgxoxklmhiltmtee" 19) => "hopeisthethingwithfeathersthatperchesinthesoulandsingsthetunewithoutthewordsandneverstopsatall"))

(facts
  (fact "decrypts"
    (core/encrypt-letter \w \c) => \y)
  (fact "decrypts"
    (core/encrypt-letter \u \i) => \c))

(fact "vigenere encrypt"
  (core/vigenere-encrypt "welcometoclojurebridge" "cipher") => "ymajsdgbdjpflcglfiklvl")