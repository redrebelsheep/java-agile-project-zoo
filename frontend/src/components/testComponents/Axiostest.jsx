import Button from '@mui/material/Button';
import AnimalService from '../../service/animal.service'

let getALLTest = () =>{
    console.log(AnimalService.getAllAnimals())
}

let getById =  async() =>{
   await console.log(AnimalService.getAnimalById(1))
}


const AxiosTest = () => {
    return (
        <>
            {/*<Button variant="outlined" onClick={() => getALLTest()}>getAll</Button>*/}
            <Button variant="outlined" onClick={() => getById()}>getById</Button>
            <Button variant="outlined">post</Button>
            <Button variant="outlined">put</Button>
            <Button variant="outlined">delete</Button>
        </>

    );
};

export default AxiosTest;