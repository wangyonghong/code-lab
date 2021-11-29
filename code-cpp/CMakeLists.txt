cmake_minimum_required(VERSION 3.20)
project(code_cpp)

set(CMAKE_CXX_STANDARD 23)

add_executable(code_cpp main.cpp)

# 添加 LeetCode 刷题目录
file(GLOB files ./algo/lc/*/*.cpp)
foreach (file ${files})
    string(REGEX REPLACE ".+/(.+)\\..*" "\\1" exe ${file})
    add_executable(${exe} ${file})
    message(\ \ \ \ --\ src/${exe}.cpp\ will\ be\ compiled\ to\ bin/${exe})
endforeach ()