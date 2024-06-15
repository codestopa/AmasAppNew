const models = require("../models");

function save(req, res){
    const receta = {
        nombre: req.body.nombre,
        metodo: req.body.metodo,
        imagen: req.body.imagen,
        duracion: req.body.duracion,
        ingredientes: req.body.ingredientes,
        dificultad: req.body.dificultad,
        valorNutricional: req.body.valorNutricional
    }
    models.Receta.create(receta).then(result => {
        res.status(201).json({
            message: "Receta creada correctamente",
            receta:result
        });
    }).catch(error => {
        res.status(500).json({
            message: "Ha habido un error",
            error:error
        });
    });

}

function show(req, res){
    const id = req.params.id;

    models.Receta.findByPk(id).then(result => {
        res.status(200).json(result);
    }).catch(error => {
        res.status(500).json({
            message: "Ha habido un error!"
        });
    })
}

module.exports = {
    save: save,
    show:show
}