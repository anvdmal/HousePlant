import React from 'react';
import PlantCard from './PlantCard';

const PlantList = ({title, plants, onWater, onWilt, onActivate}) => {
    return (
        <div className="plant-list-section">
            <h2 className="plant-list-title">{title}</h2>
            <div className="plant-list">
                {plants.map((plant) => (
                    <PlantCard
                        key={plant.id}
                        plant={plant}
                        onWater={onWater}
                        onWilt={onWilt}
                        onActivate={onActivate}
                    />
                ))}
            </div>
        </div>
    );
};

export default PlantList;
