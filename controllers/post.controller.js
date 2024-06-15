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

function index(req, res){
    models.Receta.findAll().then(result =>{
        res.status(200).json(result);
    }).catch(error =>{
        res.status(500).json({
            message: "Ha ocurrido un error!"
        })
    });
}

function update(req, res){
    const id = req.params.id;
    const updatedReceta = {
        nombre: req.body.nombre,
        metodo: req.body.metodo,
        imagen: req.body.imagen,
        duracion: req.body.duracion,
        ingredientes: req.body.ingredientes,
        dificultad: req.body.dificultad,
        valorNutricional: req.body.valorNutricional
    }
    const userId = 1;

    models.Receta.update(updatedReceta, {where: {id:id}}).then(result=>{
        res.status(200).json({
            message: "receta actualizada con éxito",
            receta:updatedReceta
        });
    }).catch(error =>{
        res.status(200).json({
            message: "algo ha salido mal!",
            error:error
        });
    })
}

function destroy(req, res){
    const id = req.params.id;

    models.Receta.destroy({where:{id:id}}).then(result =>{
        res.status(200).json({
            message: "receta eliminada con éxito",
        });
    }).catch(error =>{
        res.status(200).json({
            message: "algo ha salido mal!",
            error:error
        });
    });
}

module.exports = {
    save: save,
    show:show,
    index: index,
    update:update,
    destroy:destroy
}