/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package qbix.sm.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.AbstractImagePrototype;
import com.google.gwt.user.client.ui.ImageBundle;

/**
 *
 * @author BigBlackBug
 */
public interface Icons extends ImageBundle
{
    public static final Icons ICONS = GWT.create(Icons.class);

    @Resource("stop.jpg")
    AbstractImagePrototype locked();
}