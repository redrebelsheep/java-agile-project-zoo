import axios from 'axios';

const API_URL_ALL = "http://localhost:8080/zoo/animals"
const API_URL = "http://localhost:8080/zoo/animal"

const getAllAnimals = async () => {
    let response ="";
    try{
        response =  await axios.get(API_URL_ALL)
        console.log(`All: ${response}`)
    return await response.data;
    }catch(error){
        console.log(`Error: ${error}`)
    }
}

const getAnimalById = async (id)  =>{
    let response ="";
    try{
        response =  await axios.get(API_URL + "/" + id);
        console.log(`All: ${response.data}`)
        return  await response.json();
    }catch(error){
        console.log(`Error: ${error}`)
    }
}

const postAnimal = async (animal) => {
    return await axios.post(API_URL, animal)
}

const putAnimal = async (animal) => {
    return await axios.post(API_URL, animal)
}

const deleteAnimal = async (id) => {
    return await axios.delete(API_URL + "/" + id)
}

const exports = {
    getAllAnimals,
    getAnimalById,
    postAnimal,
    deleteAnimal
}

export default exports;

