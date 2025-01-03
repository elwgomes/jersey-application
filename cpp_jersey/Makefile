CXX = g++
CXXFLAGS = -std=c++17 -Wall -Iinclude  # Adicionado o -Iinclude

SRC = main.cpp api_client.cpp
OBJ = $(SRC:.cpp=.o)
TARGET = app

LIBS = -lcurl

all: $(TARGET)

$(TARGET): $(OBJ)
	$(CXX) -o $@ $^ $(LIBS)

%.o: %.cpp
	$(CXX) $(CXXFLAGS) -c $< -o $@

clean:
	rm -f $(OBJ) $(TARGET)

