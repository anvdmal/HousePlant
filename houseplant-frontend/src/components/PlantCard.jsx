import React from 'react';
import {PLANT_STATUS} from "../constants/PlantStatuses.js";

const PlantCard = ({plant, onWater, onWilt, onActivate}) => {
    const {id, name, plantDate, plantStatus, sunlightType, lastWatered, daysSinceWatered} = plant;

    const formatDate = (dateString) => {
        const date = new Date(dateString);
        return date.toLocaleDateString('ru-RU');
    };

    return (
        <div className="plant-card">
            <div className="plant-header">
                <h4>{name}</h4>
                <span className="plant-date">{formatDate(plantDate)}</span>
            </div>
            <div className="plant-info">
                <p><strong>Освещение:</strong></p>
                <p>{sunlightType}</p>
            </div>
            <div className="plant-info">
                <p><strong>Последний полив:</strong></p>
                <p>
                    {lastWatered
                        ? `${formatDate(lastWatered)} (${daysSinceWatered} дн. назад)`
                        : '—'}
                </p>
            </div>
            {plantStatus.toLowerCase() === PLANT_STATUS.ALIVE && (
                <div className="card-buttons">
                    <button className="water-btn" onClick={() => onWater(id)}>Полить</button>
                    <button className="wilt-btn" onClick={() => onWilt(id)}>Завяло</button>
                </div>
            )}
            {plantStatus.toLowerCase() === PLANT_STATUS.WILTED && (
                <div className="card-buttons">
                    <button className="activate-btn" onClick={() => onActivate(id)}>Оживить</button>
                </div>
            )}
        </div>
    );
};

export default PlantCard;