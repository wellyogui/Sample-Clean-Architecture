# Projeto Sample: Clean Architecture com SOLID, Hilt, Room, Coroutines e Navigation

Este projeto de exemplo é um aplicativo Android desenvolvido com base na clean architecture, utilizando os princípios do SOLID (Single Responsibility, Open/Closed, Liskov Substitution, Interface Segregation, Dependency Inversion). Ele faz uso das seguintes tecnologias e bibliotecas:

- **Hilt**: Hilt é um framework oficial do Google para injeção de dependência em aplicativos Android. Ele simplifica a configuração e o gerenciamento das dependências no código, facilitando a manutenção e a escalabilidade do projeto.
- **Room**: Room é uma biblioteca de persistência de dados que oferece uma camada de abstração sobre o SQLite no Android. Ela simplifica a criação e a manipulação de bancos de dados locais, permitindo uma integração fácil e eficiente de armazenamento de dados estruturados.
- **Coroutines**: Coroutines são uma forma de escrever código assíncrono de maneira concisa e legível no Kotlin. Elas facilitam a execução de operações longas e bloqueadoras de maneira assíncrona, ajudando a evitar bloqueios na thread principal e a melhorar a responsividade do aplicativo.
- **Navigation Component**: O Navigation Component é uma parte do Android Jetpack que facilita a implementação e a gestão da navegação entre destinos (fragments e atividades) em um aplicativo Android. Ele simplifica o gerenciamento do fluxo de navegação e a passagem de parâmetros entre diferentes telas.

## Estrutura do Projeto

O projeto segue uma arquitetura limpa com a seguinte estrutura de pacotes:

- **Módulo App**:
  - **db**: Contém as classes relacionadas à camada de dados, incluindo a configuração do banco de dados Room e as classes de acesso aos dados.
  - **di**: Contém as configurações relacionadas à injeção de dependência usando o Hilt.
  - **presentation**: Responsável por lidar com a interface do usuário, contendo Activities, Fragments, ViewModels e Adapters.

- **Módulo core**:
  - **data**: Contém as classes responsáveis pelo acesso aos dados externos e locais, como implementações de interfaces de repositório.
  - **repository**: Contém as interfaces e implementações de repositório que definem as operações disponíveis para acesso aos dados.
  - **usecase**: Contém os use cases que representam as principais funcionalidades da aplicação.

## Fluxo de Dados

1. O usuário interage com a interface do usuário na tela de lista de notas.
2. A camada de apresentação (presentation) solicita os dados necessários ao ViewModel correspondente.
3. O ViewModel chama o use case apropriado na camada de domínio (repository).
4. O use case interage com a camada de dados (data) para obter os dados necessários das notas.
5. A camada de dados utiliza o Room para acessar os dados do banco de dados local, ou outras fontes de dados, conforme necessário.
6. Os dados são retornados ao use case, que os processa conforme necessário e os retorna ao ViewModel.
7. O ViewModel atualiza a interface do usuário com os dados recebidos.

## Use Cases

Os use cases definidos na camada de domínio representam as operações que podem ser realizadas na aplicação, como listar todas as notas, criar uma nova nota, editar uma nota existente, etc. Cada use case é implementado em uma classe separada, seguindo o princípio da responsabilidade única.

## Injeção de Dependência

O Hilt é utilizado para realizar a injeção de dependência em todo o aplicativo. As dependências são configuradas utilizando módulos específicos e anotações do Hilt, o que facilita a organização e a manutenção do código.

## Persistência de Dados

A persistência de dados é gerenciada pelo Room, que fornece uma camada de abstração sobre o SQLite. O Room é utilizado para armazenar e recuperar as notas criadas pelo usuário de forma eficiente.

## Navegação

A navegação entre as telas é gerenciada pelo Navigation Component, que simplifica a definição e o controle do fluxo de navegação em todo o aplicativo. Ele permite navegar entre os destinos de forma declarativa e passar parâmetros entre as telas quando necessário.

Este projeto de exemplo demonstra como aplicar os princípios da arquitetura limpa e utilizar tecnologias modernas para desenvolver aplicativos Android robustos e escaláveis, organizando o código em pacotes que refletem claramente as responsabilidades de cada componente. Ele pode servir como uma referência para desenvolvedores que desejam aprender e aplicar esses conceitos em seus próprios projetos.
