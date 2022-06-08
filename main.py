"""
SIMPLE WEATHER PREDICTION USE ARTIFICIAL NEURAL NETWORK ALGORITHM
[Lebih disarankan menggunakan Jupyter Notebook]

1. Mengupload Data Set
"""

import pandas as pd
from google.colab import files

files = files.upload()

"""
2. Memastikan dataset terbaca
"""

import io

data = pd.read_csv(io.BytesIO(files['dataset.csv']))
data1 = data
data2 = data1
data3 = data2

print(data)

"""
Data sudah berhasil terbaca, setelah itu pilih data yang akan dipakai. 
Untuk memprediksi cuaca kita membutuhkan data :
    1. Temperature
    2. Kelembapan
    3. Kecepatan angin
Dan data target :
    4. Cuaca
"""

dataset  = data[['temp','hum','windspeed','weathersit']]
print('----------------------------DATASET--------------------------------------------')
print(dataset)

"""
3. Transformasi data ke array matrix
"""

import numpy as np

temperature = np.array(data['temp'])
Kelembapan = np.array(data1['hum'])
kecepatan_angin = np.array(data2['windspeed'])
Cuaca = np.array(data3['weathersit'])


matrix = [temperature,Kelembapan,kecepatan_angin,Cuaca]
print(np.array(matrix,ndmin=2).T)

"""
4. Menginisialisasi komponen Artificial Neural Network
    a. Learning rate
    b. Bias
    c. Bobot
"""

import random
import os

learning_rate = 1
bias = 1
weights = [random.random(),random.random(),random.random(),random.random()]

"""
Keterangan : 4 bobot untuk 3 input layer dan 4 hidden layer

5. Membuat fungsi Perceptron
"""

def Perceptron(input1,input2,input3,output):
  outputP = input1*weights[0]+input2*weights[1]+input3*weights[2]+bias*weights[3]
  outputP = 1/(1+np.exp(-outputP)) # Sigmoid
  
  error = output - outputP
  weights[0]+= error*input1*learning_rate
  weights[1]+= error*input2*learning_rate
  weights[2]+= error*input3*learning_rate
  weights[3]+= error*bias*learning_rate

"""
6. Buat perulangan untuk melatih data/training
"""

for i in range(50):
  Perceptron(temperature,Kelembapan,kecepatan_angin,Cuaca)
  Perceptron(temperature,Kelembapan,kecepatan_angin,Cuaca)
  Perceptron(temperature,Kelembapan,kecepatan_angin,Cuaca)
  Perceptron(temperature,Kelembapan,kecepatan_angin,Cuaca)
  Perceptron(temperature,Kelembapan,kecepatan_angin,Cuaca)

"""
7. Buat inputan/testing
"""

x = float(input("Masukan Nilai Temperature     :"))
y = float(input("Masukan Nilai Kelembapan      :"))
z = float(input("Masukan Nilai Kecepatan Angin :"))

outputP = x*weights[0]+y*weights[1]*z*weights[2]+bias*weights[3]
outputP = 1/(1+np.exp(-outputP))

if outputP[0] <= 1:
  outputP = 1
  outputP =str('Cerah') 
elif outputP[0] ==2:
  outputP = 2
  outputP =str('Berawan')
elif outputP[0] ==3:
  outputP = 3
  outputP =str('Gerimis')
else:
  outputP = 4
  outputP =str('Hujan')

print("")
print("Temperature :",x,"Kelembapan :",y,"WindSpeed :",z,"Prediksi :",outputP)

"""
Terimakasih.
"""