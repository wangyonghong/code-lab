# coding=utf-8
import turtle
import time

# 同时设置pencolor=color1, fillcolor=color2

turtle.color("red", "yellow")

turtle.begin_fill()
turtle.penup()
turtle.goto(-200, 0)
turtle.pendown()
turtle.speed(10)

for _ in range(36):
  turtle.forward(400)
  turtle.left(170)

turtle.end_fill()

turtle.mainloop()
