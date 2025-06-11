import React, {useState} from 'react';
import {SUNLIGHT_TYPES} from '../constants/SunlightTypes';

const PlantForm = ({addPlant}) => {
    const [form, setForm] = useState({
            name: '',
            plantDate: '',
            sunlightType: ''
        }
    );
    const [errors, setErrors] = useState({});

    const validate = () => {
        const newErrors = {};
        if (!form.name) newErrors.name = "Обязательно для заполнения";
        if (!form.plantDate) newErrors.plantDate = "Обязательно для заполнения";
        if (!form.sunlightType) newErrors.sunlightType = "Обязательно для заполнения";

        const plantDate = new Date(form.plantDate);
        if (plantDate > new Date()) {
            newErrors.plantDate = "Дата не может быть в будущем";
        }

        return newErrors;
    };

    const handleSubmit = (e) => {
        e.preventDefault();

        const validationErrors = validate();
        if (Object.keys(validationErrors).length > 0) {
            setErrors(validationErrors);
            return;
        }

        addPlant(form);

        setForm({
            name: '',
            plantDate: '',
            sunlightType: ''
        });
        setErrors({});
    };

    return (
        <div className="add-plant-section">
            <h2>Новое растение</h2>
            <form onSubmit={handleSubmit}>
                <div className="add-field">
                    <label>Название</label>
                    <input
                        type="text"
                        id="plantName"
                        placeholder="Введите название"
                        value={form.name}
                        onChange={(e) => setForm({...form, name: e.target.value})}
                        className={errors.name ? "input-error" : ""}
                    />
                    {errors.name && <span className="error-text">{errors.name}</span>}
                </div>
                <div className="add-field">
                    <label>Дата посадки</label>
                    <input
                        type="date"
                        id="plantDate"
                        placeholder="дд.мм.гггг"
                        value={form.plantDate}
                        onChange={(e) => setForm({...form, plantDate: e.target.value})}
                        className={errors.plantDate ? "input-error" : ""}
                    />
                    {errors.plantDate && <span className="error-text">{errors.plantDate}</span>}
                </div>
                <div className="add-field">
                    <label>Тип освещения</label>
                    <select
                        value={form.sunlightType}
                        onChange={(e) => setForm({...form, sunlightType: e.target.value})}
                        className={errors.sunlightType ? "input-error" : ""}
                    >
                        <option value="" disabled hidden>Выберите тип</option>
                        {SUNLIGHT_TYPES.map((type) => (
                            <option key={type.value} value={type.value}>
                                {type.label}
                            </option>
                        ))}
                    </select>
                    {errors.sunlightType && <span className="error-text">{errors.sunlightType}</span>}
                </div>
                <button type="submit">Добавить</button>
            </form>
        </div>
    );
};

export default PlantForm;