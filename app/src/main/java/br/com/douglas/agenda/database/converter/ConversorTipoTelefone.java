package br.com.douglas.agenda.database.converter;

import androidx.room.TypeConverter;

import br.com.douglas.agenda.model.TipoTelefone;

public class ConversorTipoTelefone {

    @TypeConverter
    public String toString(TipoTelefone tipo) {
        if (tipo != null) {
            return tipo.name();
        }
        return null;
    }

    @TypeConverter
    public TipoTelefone toTipoTelefone(String valor) {
        if (valor != null) {
            return TipoTelefone.valueOf(valor);
        }
        return TipoTelefone.FIXO;
    }
}
