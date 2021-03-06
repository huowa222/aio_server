/**
 * Copyright (C) 2003  Manfred Andres
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 * 
 * Created on 28.09.2003
 */

package freecs.commands;

import freecs.interfaces.ICommand;
import freecs.content.MessageState;

/**
 * @author Rene M.
 *
 * freecs.commands
 */
public class CmdFriendsOnly extends AbstractCommand {
	private final String cmd= "/fonly";
	private final String version = "1.0";
	private static final ICommand selve=new CmdFriendsOnly();

	private CmdFriendsOnly () { }
	
	public static ICommand getInstance () {
		return selve;
	}
	   
    public Object instanceForSystem() {
        return this;
    }
    
    public String getCmd() {
        return cmd;
    }
   /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

public boolean execute (MessageState msgState, String param) {
        if (msgState.sender.numberOfFriends () < 1) {
            msgState.msgTemplate = "error.fl.nofriends";
            msgState.sender.sendMessage (msgState.mp);
            return false;
        }
        if (!msgState.sender.isFriendsOnly()){
		    msgState.msgTemplate = "message.fonly.true";
		    msgState.sender.setFriendsOnly(true);
        }  else {
            msgState.msgTemplate = "message.fonly.false";
            msgState.sender.setFriendsOnly(false);
        }
 		msgState.sender.sendMessage (msgState.mp);
		return true;
	}
}
