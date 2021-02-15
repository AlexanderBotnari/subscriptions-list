package mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import entities.Message;

public class MessageRowMapper implements RowMapper<Message>{
    @Override
    public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Message message = new Message();
    	message.setId(rs.getInt("id"));
    	message.setContent(rs.getString("content"));
 
        return message;
    }
}
