class Animal {
        #id;
        #name;
        #species;
        #subsistenceCosts;
        #enclosure;
        #vet;

        static Builder = class {
            #id;
            #name;
            #species;
            #subsistenceCosts;
            #enclosure;
            #vet;

            setId(id) {
                this.#id = id
                return this
            }
            setName(name){
                this.#name = name;
                return this
            }


        }


    constructor(id, name, species, subsistenceCosts, enclosure, vet) {
        this.#id = id;
        this.#name = name;
        this.#species = species;
        this.#subsistenceCosts = subsistenceCosts;
        this.#enclosure = enclosure;
        this.#vet = vet;
    }

}


