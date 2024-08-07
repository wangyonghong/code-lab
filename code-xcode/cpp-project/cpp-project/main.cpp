//
//  main.cpp
//  cpp-project
//
//  Created by 永红 on 2024/2/11.
//

#include <iostream>


class Entity {
public:
    Entity() {
        std::cout<< "create" << std::endl;
    }
    
    ~Entity() {
        std::cout<< "destory" << std::endl;
    }
};

int main(int argc, const char * argv[]) {
    // insert code here...
    std::cout << "Hello, World!\n";
    {
        std::unique_ptr<Entity> entity = std::make_unique<Entity>();
    }
    std::cin.get();
    return 0;
}
