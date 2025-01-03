#include "api_client.h"
#include <iostream>

int main() {
  int option;
  std::cout << "Escolha a operação desejada:\n";
  std::cout << "1. Consultar (GET)\n";
  std::cout << "2. Criar (POST)\n";
  std::cout << "3. Atualizar (PUT)\n";
  std::cout << "4. Deletar (DELETE)\n";
  std::cout << "Digite a opção (1-4): ";
  std::cin >> option;

  if (option == 1) {
    getUsers();
  } else if (option == 2) {
    std::string firstName, lastName, email, avatar;
    std::cout << "Digite o primeiro nome: ";
    std::cin >> firstName;
    std::cout << "Digite o sobrenome: ";
    std::cin >> lastName;
    std::cout << "Digite o email: ";
    std::cin >> email;
    std::cout << "Digite o avatar: ";
    std::cin >> avatar;
    createUser(firstName, lastName, email, avatar);
  } else if (option == 3) {
    int userId;
    std::string firstName, lastName, email, avatar;
    std::cout << "Digite o ID do usuário a ser atualizado: ";
    std::cin >> userId;
    std::cout << "Digite o primeiro nome: ";
    std::cin >> firstName;
    std::cout << "Digite o sobrenome: ";
    std::cin >> lastName;
    std::cout << "Digite o email: ";
    std::cin >> email;
    std::cout << "Digite o avatar: ";
    std::cin >> avatar;
    updateUser(userId, firstName, lastName, email, avatar);
  } else if (option == 4) {
    int userId;
    std::cout << "Digite o ID do usuário a ser deletado: ";
    std::cin >> userId;
    deleteUser(userId);
  } else {
    std::cerr << "Opção inválida." << std::endl;
  }

  return 0;
}
