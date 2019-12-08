# Setup do Projeto

## Requisitos de ferramentas para o setup

1) Jdk 8 (tutorial não foi testado com outras versões do JDK)
1.1) Avaliar uso do SDK Man para simplificar gerenciamento de várias versões do Java (https://sdkman.io/) (https://hackernoon.com/using-sdkman-to-manage-java-versions-7fde0d38c501)
2) Instalação do [leiningen](https://leiningen.org) 
3) Fazer download e instalar IDE [IntelliJ] https://www.jetbrains.com/idea/download/
4) Instalar Plugin Cursive no IntelliJ (digitar *Cursive* no Marketplace)
5) Criar um diretório novo (mkdir clojure-ciphers) em uma pasta qualquer de seu computador e essa pasta será referência

## Criando o projeto

1) Criar um novo projeto clojure com leiningen dentro do diretório criado no passo 4 da etapa anterior para o projeto executando no terminal: ```$ lein new cipher``` . Será criado uma pasta chamada cipher no diretório clojure-ciphers com a estrutura:
```CHANGELOG.md  doc  LICENSE  project.clj  README.md  resources  src  test```

2) Abrir/Importar (Menu File -> Open) projeto criado no passo 1.
2.1) Import project from external model (Leiningen)

3) Editar o arquivo project.clj para adicionar o [`Midje`](https://github.com/marick/Midje) como dependência do projeto. E adicionar o plugin `lein-midje` para executarmos os testes.

``` clojure
(defproject cipher "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-midje "3.2.1"]]
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [midje "1.9.0"]])

```

4) Atualizar dependencias no IntelliJ (para que o editor consiga fazer autocomplete e o highlight corretamente do código)

![screen shot 2017-12-05 at 19 01 52](https://user-images.githubusercontent.com/1187561/33630613-252c3468-d9ef-11e7-8544-64096a70f20d.png)

5) Substituir o conteúdo do arquivo `core_test.clj` (diretório do projeto: _test/cipher_) com o valor abaixo:
``` clojure
(ns cipher.core-test
  (:require [cipher.core :as core]
            [midje.sweet :refer :all]))

(fact "this will fail"
      1 => 2)
```

> Obs. Note que mudamos aqui também o require do namespace **cipher.core** de `[cipher.core :refer :all]` para `[cipher.core :as core]`.
Com isso criamos um _alias_ para este namespace, esta é uma boa prática pois evita o conflito de funções além de ajudar na leitura e organização do código.

6) Clique com botão direito do mouse no arquivo `core_test.clj`, e escolha opção "Open In Terminal". Vai abrir uma sessão do terminal dentro do IntelliJ e você deve digitar o comando abaixo:

```$ lein midje :autotest```

> O autotest vai executar os testes a cada mudança no código :) 

No seu terminal veja que o novo teste já foi executado e deve ter um resultado como:
```
FAIL "this will fail" at (core_test.clj:7)
Expected:
2
Actual:
1
```

> **Faça esse teste passar :)**

**Próximo:** [Visão geral de Clojure](2-functional-overview.md)

