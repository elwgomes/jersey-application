#include "api_client.h"
#include "json.hpp"
#include <curl/curl.h>
#include <iostream>

using json = nlohmann::json;

// Callback para escrever os dados da resposta
size_t WriteCallback(void *contents, size_t size, size_t nmemb,
                     std::string *userp) {
  userp->append((char *)contents, size * nmemb);
  return size * nmemb;
}

void getUsers() {
  CURL *curl = curl_easy_init();
  if (curl) {
    std::string readBuffer;
    curl_easy_setopt(curl, CURLOPT_URL,
                     "http://localhost:8080/jersey/api/users");
    curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, WriteCallback);
    curl_easy_setopt(curl, CURLOPT_WRITEDATA, &readBuffer);
    CURLcode res = curl_easy_perform(curl);
    curl_easy_cleanup(curl);

    if (res == CURLE_OK) {
      std::cout << "Usuários encontrados:\n" << readBuffer << std::endl;
    } else {
      std::cerr << "Erro ao buscar usuários: " << curl_easy_strerror(res)
                << std::endl;
    }
  }
}

void createUser(const std::string &firstName, const std::string &lastName,
                const std::string &email, const std::string &avatar) {
  CURL *curl = curl_easy_init();
  if (curl) {
    std::string readBuffer;
    json jsonData = {{"first_name", firstName},
                     {"last_name", lastName},
                     {"email", email},
                     {"avatar", avatar}};

    std::string payload = jsonData.dump();
    curl_easy_setopt(curl, CURLOPT_URL,
                     "http://localhost:8080/jersey/api/users");
    curl_easy_setopt(curl, CURLOPT_POST, 1L);
    curl_easy_setopt(curl, CURLOPT_POSTFIELDS, payload.c_str());

    struct curl_slist *headers = NULL;
    headers = curl_slist_append(headers, "Content-Type: application/json");
    curl_easy_setopt(curl, CURLOPT_HTTPHEADER, headers);

    curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, WriteCallback);
    curl_easy_setopt(curl, CURLOPT_WRITEDATA, &readBuffer);
    CURLcode res = curl_easy_perform(curl);
    curl_easy_cleanup(curl);

    if (res == CURLE_OK) {
      std::cout << "Resposta da API:\n" << readBuffer << std::endl;
    } else {
      std::cerr << "Erro ao criar usuário: " << curl_easy_strerror(res)
                << std::endl;
    }
  }
}

void updateUser(int userId, const std::string &firstName,
                const std::string &lastName, const std::string &email,
                const std::string &avatar) {
  CURL *curl = curl_easy_init();
  if (curl) {
    std::string readBuffer;
    json jsonData = {{"first_name", firstName},
                     {"last_name", lastName},
                     {"email", email},
                     {"avatar", avatar}};

    std::string payload = jsonData.dump();
    std::string url =
        "http://localhost:8080/jersey/api/users/" + std::to_string(userId);
    curl_easy_setopt(curl, CURLOPT_URL, url.c_str());
    curl_easy_setopt(curl, CURLOPT_CUSTOMREQUEST, "PUT");
    curl_easy_setopt(curl, CURLOPT_POSTFIELDS, payload.c_str());

    struct curl_slist *headers = NULL;
    headers = curl_slist_append(headers, "Content-Type: application/json");
    curl_easy_setopt(curl, CURLOPT_HTTPHEADER, headers);

    curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, WriteCallback);
    curl_easy_setopt(curl, CURLOPT_WRITEDATA, &readBuffer);
    CURLcode res = curl_easy_perform(curl);
    curl_easy_cleanup(curl);

    if (res == CURLE_OK) {
      std::cout << "Usuário atualizado com sucesso:\n"
                << readBuffer << std::endl;
    } else {
      std::cerr << "Erro ao atualizar usuário: " << curl_easy_strerror(res)
                << std::endl;
    }
  }
}

void deleteUser(int userId) {
  CURL *curl = curl_easy_init();
  if (curl) {
    std::string url =
        "http://localhost:8080/jersey/api/users/" + std::to_string(userId);
    curl_easy_setopt(curl, CURLOPT_URL, url.c_str());
    curl_easy_setopt(curl, CURLOPT_CUSTOMREQUEST, "DELETE");

    CURLcode res = curl_easy_perform(curl);
    curl_easy_cleanup(curl);

    if (res == CURLE_OK) {
      std::cout << "Usuário deletado com sucesso." << std::endl;
    } else {
      std::cerr << "Erro ao deletar usuário: " << curl_easy_strerror(res)
                << std::endl;
    }
  }
}
