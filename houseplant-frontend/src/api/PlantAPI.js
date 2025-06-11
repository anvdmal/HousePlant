import axios from 'axios';

const plantAPI = axios.create({
        baseURL: "http://localhost:8080/house/plants",
    }
);

export const getPlantsByStatus = async (status) => {
    const response = await plantAPI
        .get(`/${status}`)
        .catch((error) => {
            console.error("Ошибка получения растений по статусу:",
                error);
        });
    return response.data;
}

export const addPlant = async (plant) => {
    const response = await plantAPI
        .post("/add", {
            name: plant.name,
            plantDate: plant.plantDate,
            sunlightType: plant.sunlightType,
        })
        .catch((error) => {
            console.error("Ошибка добавления нового растения: ",
                error);
        });
    return response.data;
}

export const waterPlant = async (id) => {
    const response = await plantAPI
        .post(`/${id}/watering`)
        .catch((error) => {
            console.error("Ошибка при поливе растения: ",
                error);
        });
    return response.data;
}

export const wiltPlant = async (id) => {
    const response = await plantAPI
        .post(`/${id}/wilt`)
        .catch((error) => {
            console.error("Ошибка при удалении растения: ",
                error);
        });
    return response.data;
}

export const activatePlant = async (id) => {
    const response = await plantAPI
        .post(`/${id}/activate`)
        .catch((error) => {
            console.error("Ошибка при активации растения: ",
                error);
        });
    return response.data;
}