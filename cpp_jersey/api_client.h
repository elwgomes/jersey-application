#ifndef API_CLIENT_H
#define API_CLIENT_H

#include <string>

void getUsers();
void createUser(const std::string& firstName, const std::string& lastName, const std::string& email, const std::string& avatar);
void updateUser(int userId, const std::string& firstName, const std::string& lastName, const std::string& email, const std::string& avatar);
void deleteUser(int userId);

#endif
