import {useEffect, useState} from 'react';
import AddPlantForm from './components/AddPlantForm';
import PlantList from './components/PlantList';
import {
    getPlantsByStatus,
    addPlant,
    waterPlant,
    wiltPlant,
    activatePlant
} from './api/PlantAPI';
import './App.css';
import {PLANT_STATUS} from "./constants/PlantStatuses.js";

const App = () => {
    const [alivePlants, setAlivePlants] = useState([]);
    const [wiltedPlants, setWiltedPlants] = useState([]);
    const [errorMessage, setErrorMessage] = useState("");

    useEffect(() => {
        async function fetchPlants() {
            try {
                const alive = await getPlantsByStatus(PLANT_STATUS.ALIVE);
                setAlivePlants(alive || []);
                const wilted = await getPlantsByStatus(PLANT_STATUS.WILTED);
                setWiltedPlants(wilted || []);
            } catch (error) {
                if (!error.response) {
                    setErrorMessage("Извините, сервер недоступен. Повторите попытку позже.");
                } else {
                    setErrorMessage("Произошла ошибка при загрузке растений.");
                }
            }
        }
        fetchPlants();
    }, []);

    const handleAddPlant = async (plant) => {
        try {
            const createdPlant = await addPlant(plant);
            setAlivePlants(prev => [...prev, createdPlant]);
        } catch (error) {
            setErrorMessage("Произошла ошибка при добавлении растения.");
        }
    };

    const handleWater = async (id) => {
        try {
            const plant = await waterPlant(id);
            setAlivePlants(prev => prev.map((p) => (p.id === id ? plant : p)))
        } catch (error) {
            setErrorMessage("Произошла ошибка при поливе растения.")
        }
    };

    const handleWilt = async (id) => {
        try {
            const plant = await wiltPlant(id);
            setAlivePlants(prev => prev.filter(p => p.id !== id));
            setWiltedPlants(prev => [...prev, plant]);
        } catch (error) {
            setErrorMessage("Произошла ошибка при изменении статуса растения на \"Увядшее\".")
        }
    };

    const handleActivate = async (id) => {
        try {
            const plant = await activatePlant(id);
            setWiltedPlants(prev => prev.filter(p => p.id !== id));
            setAlivePlants(prev => [...prev, plant]);
        } catch (error) {
            setErrorMessage("Произошла ошибка при изменении статуса растения на \"Живое\".")
        }
    };

    return (
        <div className="app">
            <h1><span>HousePlant</span> — ваша домашняя оранжерея под контролем!</h1>
            {errorMessage && (
                <h2 className="error-text">
                    {errorMessage}
                </h2>
            )}
            <div className="main">
                <aside className="add-section">
                    <AddPlantForm addPlant={handleAddPlant}/>
                </aside>
                <section className="plant-section">
                    <div className="plant-columns">
                        <div className="plant-column">
                            <PlantList
                                title="Живые растения"
                                plants={alivePlants}
                                onWater={handleWater}
                                onWilt={handleWilt}
                            />
                        </div>
                        <div className="separator"/>
                        <div className="plant-column">
                            <PlantList
                                title="Увядшие растения"
                                plants={wiltedPlants}
                                onActivate={handleActivate}
                            />
                        </div>
                    </div>
                </section>
            </div>
            <footer className="footer">
                <p>© 2025 anvdmal</p>
            </footer>
        </div>
    );
};

export default App;