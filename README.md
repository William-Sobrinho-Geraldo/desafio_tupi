<img width=100% src="https://capsule-render.vercel.app/api?type=waving&color=4278e3&height=120&section=header"/>

# Teste Técnico Tupi Fintech - Fluxo EMV 

## Resumo 
Este projeto trata-se de um teste técnico da empresa Tupi Fintech no qual o objetivo era criar um módulo básico de processamento de transações EMV, que valide e simule a comunicação entre um terminal de pagamento (POS) e um cartão de crédito/débito com chip.

## Imagens do App

<div align="center"> 
<img src = "https://github.com/William-Sobrinho-Geraldo/desafio_tupi/blob/develop/image.png?raw=true" width = "150px" >
<img src = "https://github.com/William-Sobrinho-Geraldo/desafio_tupi/blob/develop/imagem%201%20desafio%20tupi.png?raw=true" width = "150px" >

</div>

</br>

## Requisitos exigidos pela Tupi

- Linguagem: Escolha entre Golang, Kotlin, Java ou Rust.
- Protocolo EMV: Implementar uma lógica básica para processar uma transação com base em um conjunto de TLVs (Tag-Length-Value).
- Fluxo da Transação:</br>
   Entrada de dados do cartão (simulado).</br>
   Decodificação de TLVs da transação.</br>
   Validação de dados essenciais (PAN, data de validade, CVM).</br>
   Simulação de comunicação com gateway de pagamento para autorização da transação.</br>
   Retorno do resultado da transação (aprovada/rejeitada).
- Testes: Criar casos de teste unitários para validar a implementação.

## Detalhes do desenvolvimento do App

Este App foi totalmente escrito na linguagem [Kotlin](https://developer.android.com/kotlin?hl=pt-br), através da plataforma Android Studio. Para criação das telas de UI foram utilizados componentes do [Material Design](https://m2.material.io/design) da Google.

A arquitetura escolhida foi a [MVVM](https://developer.android.com/topic/libraries/architecture/viewmodel?hl=pt-br) que é indicada pela Google por sua capacidade de ser facilmente escalável.

Os dados do cartão poderiam ser mockados/fictícios mas decidi usar a interatividade com o usuário para validar ou não os inputs fornecidos.

Para criação e busca de Logs salvos localmente no aparelho foi utilizada a biblioteca [ROOM](https://developer.android.com/training/data-storage/room?hl=pt-br) associada a [Coroutines](https://developer.android.com/kotlin/coroutines?hl=pt-br) para que a Main Thread não fosse impactada.

A validação do PAN do cartão segue o [Algoritmo de Luhn](https://medium.com/@gomesronaldy/o-algoritmo-de-luhn-mais-usado-do-que-voc%C3%AA-imagina-987cf5f998f3).

Implementação de [Testes Unitários](https://developer.android.com/training/testing/local-tests?hl=pt-br) para as funções de validação do cartão usando a biblioteca [JUnit](https://developer.android.com/training/testing/local-tests?hl=pt-br).


## Como rodar o projeto
Para executar o projeto, basta seguir as instruções de um dos dois métodos abaixo:</br>

#### Método 1
1- Baixar o Código do GitHub como ZIP no link abaixo</br>
https://github.com/William-Sobrinho-Geraldo/desafio_tupi</br>
2- Extrair o arquivo zip e abri-lo no Android Studio</br>
3- Sincronizar os arquivos do Gradle clicando no icone abaixo</br>
<div align="start"> 
<img src = "https://github.com/William-Sobrinho-Geraldo/desafio_tupi/blob/develop/app/src/main/res/drawable/sinc_gradle.png?raw=true" width = "450px" style="margin-left: 30px;">
</div>
4- Rodar o projeto em um emulador Android clicando no botão abaixo</br>
<div align="start"> 
<img src = "https://github.com/William-Sobrinho-Geraldo/desafio_tupi/blob/develop/app/src/main/res/drawable/rodar_projeto.png?raw=true" width = "450px" style="margin-left: 30px;" >
</div>


#### Método 2
1- Fazer o Download do APK do desafio com o link do meu Google Drive abaixo</br>
https://drive.google.com/file/d/1dinOLDQgZyFbJ_4edT86td8EGzS6955J/view?usp=sharing</br>
2- Instalar o APK em qualquer dispositivo Android e usá-lo normalmente</br>


## Como Utilizar o App

O retorno da transação é aleatório, negação ou aprovação, porém o projeto verifica se o número do cartão está de acordo com o **algoritmo de Luhn**, segue abaixo alguns números PAN que podem ser utilizados como testes:
- 4556 7375 8689 9855
- 4556 7375 8689 9855
- 5100 3617 2816 3639

A **data de validade** precisa ser uma data posterior ao mês atual enquanto o **método de verificação CVM** também é gerado de forma aleatória.

Utilizei um botão no rodapé da tela principal para **mostrar e ocultar os logs** que foram salvos localmente no cache do aparelho</br>
**OBS:** Caso o App seja desinstalado os dados salvos localmente serão perdidos.


## Agradecimentos
Fico feliz por participar do processo seletivo para Desenvolvedor Android na Tupi. O teste foi desafiador exigindo não apenas conhecimentos técnicos, mas também a capacidade de resolver problemas que não estão na minha rotina como desenvolvedor Android.

Estou realmente animado com a possibilidade de colaborar com uma empresa de alta tecnologia, especialmente uma que valoriza princípios tão importantes como Respeito, Ética, Empatia e Comprometimento. Seria incrível fazer parte de um time que combina inovação com valores tão sólidos!

Nos vemos na próxima fase , um abraço.  😃
